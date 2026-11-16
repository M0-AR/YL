package Exercise_38;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Chapter38 {
    public static String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder escaped = new StringBuilder();
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            switch (character) {
                case '&' -> escaped.append("&amp;");
                case '<' -> escaped.append("&lt;");
                case '>' -> escaped.append("&gt;");
                case '"' -> escaped.append("&quot;");
                case '\'' -> escaped.append("&#39;");
                default -> escaped.append(character);
            }
        }
        return escaped.toString();
    }

    public static String render(String template, Map<String, String> values) {
        if (template == null) {
            throw new IllegalArgumentException("template is required");
        }
        Map<String, String> model = values == null ? Map.of() : values;
        StringBuilder rendered = new StringBuilder();
        int index = 0;
        while (index < template.length()) {
            int start = template.indexOf("{{", index);
            if (start < 0) {
                rendered.append(template, index, template.length());
                break;
            }
            int end = template.indexOf("}}", start + 2);
            if (end < 0) {
                rendered.append(template, index, template.length());
                break;
            }
            rendered.append(template, index, start);
            String key = template.substring(start + 2, end).trim();
            if (key.isEmpty()) {
                rendered.append(template, start, end + 2);
            } else {
                rendered.append(escapeHtml(model.getOrDefault(key, "")));
            }
            index = end + 2;
        }
        return rendered.toString();
    }

    public static String repeatBlock(String block, List<Map<String, String>> rows) {
        if (block == null || rows == null) {
            throw new IllegalArgumentException("block and rows are required");
        }
        StringBuilder rendered = new StringBuilder();
        for (int index = 0; index < rows.size(); index++) {
            Map<String, String> row = rows.get(index) == null
                    ? new LinkedHashMap<>()
                    : new LinkedHashMap<>(rows.get(index));
            row.put("@index", String.valueOf(index + 1));
            rendered.append(render(block, row));
        }
        return rendered.toString();
    }

    public static void main(String[] args) {
        System.out.println(escapeHtml("<strong>Ada & Grace</strong>"));
        System.out.println(render("<p>{{name}}</p>", Map.of("name", "Ada")));
        System.out.println(repeatBlock("<li>{{@index}} {{name}}</li>", List.of(
                Map.of("name", "Ada"), Map.of("name", "Grace"))));
    }
}
