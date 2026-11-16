package Exercise_31;

import java.util.Locale;

public class Chapter31 {
    public record Endpoint(String host, int port) {
        public Endpoint {
            if (host == null || host.isBlank()) {
                throw new IllegalArgumentException("host is required");
            }
            if (port < 0 || port > 65535) {
                throw new IllegalArgumentException("port is out of range");
            }
            host = host.trim().toLowerCase(Locale.ROOT);
        }
    }

    public record RequestLine(String method, String target, String version) {
        public RequestLine {
            if (method == null || !method.matches("[A-Z]+")) {
                throw new IllegalArgumentException("method must be uppercase letters");
            }
            if (target == null || !target.startsWith("/")) {
                throw new IllegalArgumentException("target must start with a slash");
            }
            if (version == null || !version.matches("HTTP/\\d\\.\\d")) {
                throw new IllegalArgumentException("version must look like HTTP/1.1");
            }
        }
    }

    public enum StatusClass {
        INFORMATIONAL,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR
    }

    public static Endpoint parseEndpoint(String text) {
        if (text == null) {
            throw new IllegalArgumentException("endpoint is required");
        }
        String value = text.trim();
        int colon = value.lastIndexOf(':');
        if (colon <= 0 || colon == value.length() - 1) {
            throw new IllegalArgumentException("expected host:port");
        }
        String host = value.substring(0, colon);
        int port;
        try {
            port = Integer.parseInt(value.substring(colon + 1).trim());
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("port must be a number", error);
        }
        return new Endpoint(host, port);
    }

    public static RequestLine parseRequestLine(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("request line is required");
        }
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("expected method target version");
        }
        return new RequestLine(parts[0], parts[1], parts[2]);
    }

    public static StatusClass statusClass(int status) {
        if (status < 100 || status > 599) {
            throw new IllegalArgumentException("status must be between 100 and 599");
        }
        return switch (status / 100) {
            case 1 -> StatusClass.INFORMATIONAL;
            case 2 -> StatusClass.SUCCESS;
            case 3 -> StatusClass.REDIRECTION;
            case 4 -> StatusClass.CLIENT_ERROR;
            case 5 -> StatusClass.SERVER_ERROR;
            default -> throw new IllegalArgumentException("unknown status class");
        };
    }

    public static void main(String[] args) {
        System.out.println(parseEndpoint("api.atlas.dev:8443"));
        System.out.println(parseRequestLine("GET /chapters/31?mode=compact HTTP/1.1"));
        System.out.println(statusClass(404));
    }
}
