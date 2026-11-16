package Exercise_32;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Chapter32 {
    private static final Set<String> RESERVED = Set.of(
            "select", "insert", "update", "delete", "drop",
            "table", "where", "from", "order", "group", "user");

    public static String jdbcUrl(String engine, String host, int port, String database) {
        if (engine == null || !engine.matches("[a-z0-9]+")) {
            throw new IllegalArgumentException("engine must be lowercase letters or digits");
        }
        if (host == null || host.isBlank()) {
            throw new IllegalArgumentException("host is required");
        }
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("port is out of range");
        }
        if (database == null || !database.matches("[A-Za-z0-9_]+")) {
            throw new IllegalArgumentException("database must be a simple name");
        }
        return "jdbc:" + engine + "://" + host.trim() + ":" + port + "/" + database;
    }

    public static String insertSql(String table, List<String> columns) {
        String safeTable = safeIdentifier(table);
        if (columns == null || columns.isEmpty()) {
            throw new IllegalArgumentException("at least one column is required");
        }
        List<String> safeColumns = new ArrayList<>();
        for (String column : columns) {
            safeColumns.add(safeIdentifier(column));
        }
        String placeholders = String.join(", ", safeColumns.stream().map(name -> "?").toList());
        return "INSERT INTO " + safeTable + " (" + String.join(", ", safeColumns) + ") VALUES (" + placeholders + ")";
    }

    public static String safeIdentifier(String name) {
        if (name == null) {
            throw new IllegalArgumentException("identifier is required");
        }
        String value = name.trim();
        if (!value.matches("[A-Za-z_][A-Za-z0-9_]*")) {
            throw new IllegalArgumentException("identifier must start with a letter or underscore");
        }
        if (RESERVED.contains(value.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException("reserved word cannot be used as an identifier");
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(jdbcUrl("postgresql", "db.atlas.dev", 5432, "learning"));
        System.out.println(insertSql("learners", List.of("name", "chapter")));
        System.out.println(safeIdentifier("completed_at"));
    }
}
