package Exercise_37;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Chapter37 {
    public record Response(int status, String body) {
        public Response {
            if (status < 100 || status > 599) {
                throw new IllegalArgumentException("status is out of range");
            }
            if (body == null) {
                body = "";
            }
        }
    }

    public static Map<String, String> queryParams(String query) {
        Map<String, String> values = new LinkedHashMap<>();
        if (query == null || query.isBlank()) {
            return Map.copyOf(values);
        }
        for (String segment : query.split("&")) {
            if (segment.isEmpty()) {
                continue;
            }
            int equals = segment.indexOf('=');
            String key = equals < 0 ? segment : segment.substring(0, equals);
            String value = equals < 0 ? "" : segment.substring(equals + 1);
            values.put(
                    URLDecoder.decode(key, StandardCharsets.UTF_8),
                    URLDecoder.decode(value, StandardCharsets.UTF_8));
        }
        return Map.copyOf(values);
    }

    public static String route(String method, String path) {
        if (method == null || path == null) {
            throw new IllegalArgumentException("method and path are required");
        }
        String verb = method.trim().toUpperCase(Locale.ROOT);
        String target = path.trim();
        int question = target.indexOf('?');
        if (question >= 0) {
            target = target.substring(0, question);
        }
        return switch (target) {
            case "/hello", "/echo" -> verb.equals("GET") ? "hello" : "method-not-allowed";
            case "/submit" -> verb.equals("POST") ? "submit" : "method-not-allowed";
            default -> "not-found";
        };
    }

    public static int statusFor(String routeName) {
        if (routeName == null) {
            throw new IllegalArgumentException("route name is required");
        }
        return switch (routeName) {
            case "hello", "echo" -> 200;
            case "submit" -> 201;
            case "not-found" -> 404;
            case "method-not-allowed" -> 405;
            default -> 500;
        };
    }

    public static void main(String[] args) {
        System.out.println(queryParams("name=Ada+Lovelace&chapter=37"));
        System.out.println(route("GET", "/hello?name=Ada"));
        System.out.println(new Response(statusFor(route("POST", "/submit")), "created"));
    }
}
