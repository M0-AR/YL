package Exercise_35;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Chapter35 {
    public record Row(int id, String name, int amount) {
        public Row {
            if (id < 0) {
                throw new IllegalArgumentException("id must be non-negative");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("name is required");
            }
            name = name.trim();
        }
    }

    public record Order(int id, int customerId, int total) {
        public Order {
            if (id < 0 || customerId < 0) {
                throw new IllegalArgumentException("ids must be non-negative");
            }
        }
    }

    public static Row normalizeRow(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("row is required");
        }
        String[] parts = line.split(",", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException("expected id,name,amount");
        }
        int id;
        int amount;
        try {
            id = Integer.parseInt(parts[0].trim());
            amount = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("id and amount must be integers", error);
        }
        return new Row(id, parts[1], amount);
    }

    public static List<String> joinRows(List<Row> rows, List<Order> orders) {
        if (rows == null || orders == null) {
            throw new IllegalArgumentException("rows and orders are required");
        }
        Map<Integer, String> names = new TreeMap<>();
        for (Row row : rows) {
            names.put(row.id(), row.name());
        }
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(Comparator.comparingInt(Order::id));
        List<String> joined = new ArrayList<>();
        for (Order order : sorted) {
            String name = names.get(order.customerId());
            if (name != null) {
                joined.add(name + ":" + order.total());
            }
        }
        return List.copyOf(joined);
    }

    public static Map<Integer, Integer> totalsByCustomer(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders are required");
        }
        Map<Integer, Integer> totals = new TreeMap<>();
        for (Order order : orders) {
            totals.merge(order.customerId(), order.total(), Integer::sum);
        }
        return totals;
    }

    public static void main(String[] args) {
        System.out.println(normalizeRow("7, Ada Lovelace, 120"));
        System.out.println(joinRows(
                List.of(new Row(1, "Ada", 0), new Row(2, "Grace", 0)),
                List.of(new Order(10, 2, 80), new Order(11, 1, 120), new Order(12, 9, 40))));
        System.out.println(totalsByCustomer(List.of(
                new Order(1, 1, 50), new Order(2, 1, 70), new Order(3, 2, 30))));
    }
}
