package Exercise_33;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chapter33 {
    public static String evaluate(String expression, Map<String, String> model) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is required");
        }
        Map<String, String> values = model == null ? Map.of() : model;
        StringBuilder rendered = new StringBuilder();
        int index = 0;
        while (index < expression.length()) {
            int start = expression.indexOf("#{", index);
            if (start < 0) {
                rendered.append(expression, index, expression.length());
                break;
            }
            int end = expression.indexOf('}', start + 2);
            if (end < 0) {
                rendered.append(expression, index, expression.length());
                break;
            }
            rendered.append(expression, index, start);
            String key = expression.substring(start + 2, end).trim();
            rendered.append(values.getOrDefault(key, ""));
            index = end + 1;
        }
        return rendered.toString();
    }

    public static List<String> validate(String value, boolean required, int maxLength) {
        if (maxLength < 0) {
            throw new IllegalArgumentException("maxLength must be non-negative");
        }
        String text = value == null ? "" : value.trim();
        List<String> errors = new ArrayList<>();
        if (required && text.isEmpty()) {
            errors.add("value is required");
        }
        if (!text.isEmpty() && text.length() > maxLength) {
            errors.add("value is too long");
        }
        return List.copyOf(errors);
    }

    public static String navigate(String outcome, Map<String, String> rules) {
        if (outcome == null || outcome.isBlank()) {
            throw new IllegalArgumentException("outcome is required");
        }
        if (rules == null) {
            return "index.xhtml";
        }
        String page = rules.get(outcome.trim());
        return page == null || page.isBlank() ? "index.xhtml" : page.trim();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("Welcome, #{learner.name}!", Map.of("learner.name", "Ada")));
        System.out.println(validate("", true, 40));
        System.out.println(navigate("saved", Map.of("saved", "detail.xhtml")));
    }
}
