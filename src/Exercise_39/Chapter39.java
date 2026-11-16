package Exercise_39;

import java.util.LinkedHashMap;
import java.util.Map;

public class Chapter39 {
    public enum StatusFamily {
        INFORMATIONAL,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR
    }

    private static final String NUMBER = "-?(0|[1-9][0-9]*)(\\.[0-9]+)?([eE][+-]?[0-9]+)?";

    public static String jsonEscape(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value is required");
        }
        StringBuilder escaped = new StringBuilder();
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            switch (character) {
                case '"' -> escaped.append("\\\"");
                case '\\' -> escaped.append("\\\\");
                case '\b' -> escaped.append("\\b");
                case '\f' -> escaped.append("\\f");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                default -> {
                    if (character < 0x20) {
                        escaped.append(String.format("\\u%04x", (int) character));
                    } else {
                        escaped.append(character);
                    }
                }
            }
        }
        return escaped.toString();
    }

    public static Map<String, String> parseFlatJson(String json) {
        if (json == null) {
            throw new IllegalArgumentException("json is required");
        }
        return new Parser(json).parse();
    }

    public static StatusFamily statusFamily(int status) {
        if (status < 100 || status > 599) {
            throw new IllegalArgumentException("status must be between 100 and 599");
        }
        return switch (status / 100) {
            case 1 -> StatusFamily.INFORMATIONAL;
            case 2 -> StatusFamily.SUCCESS;
            case 3 -> StatusFamily.REDIRECTION;
            case 4 -> StatusFamily.CLIENT_ERROR;
            case 5 -> StatusFamily.SERVER_ERROR;
            default -> throw new IllegalArgumentException("unknown status family");
        };
    }

    private static final class Parser {
        private final String text;
        private int index;

        Parser(String text) {
            this.text = text;
        }

        Map<String, String> parse() {
            Map<String, String> values = parseObject();
            skip();
            if (index != text.length()) {
                throw new IllegalArgumentException("trailing characters");
            }
            return values;
        }

        private Map<String, String> parseObject() {
            Map<String, String> values = new LinkedHashMap<>();
            skip();
            expect('{');
            skip();
            if (peek() == '}') {
                index++;
                return values;
            }
            while (true) {
                skip();
                String key = parseString();
                skip();
                expect(':');
                skip();
                String value = parseValue();
                if (values.put(key, value) != null) {
                    throw new IllegalArgumentException("duplicate key: " + key);
                }
                skip();
                char separator = next();
                if (separator == '}') {
                    return values;
                }
                if (separator != ',') {
                    throw new IllegalArgumentException("expected comma or closing brace");
                }
            }
        }

        private String parseString() {
            expect('"');
            StringBuilder value = new StringBuilder();
            while (true) {
                char character = next();
                if (character == '"') {
                    return value.toString();
                }
                if (character == '\\') {
                    char escape = next();
                    switch (escape) {
                        case '"' -> value.append('"');
                        case '\\' -> value.append('\\');
                        case '/' -> value.append('/');
                        case 'b' -> value.append('\b');
                        case 'f' -> value.append('\f');
                        case 'n' -> value.append('\n');
                        case 'r' -> value.append('\r');
                        case 't' -> value.append('\t');
                        case 'u' -> value.append((char) Integer.parseInt(read(4), 16));
                        default -> throw new IllegalArgumentException("bad escape: " + escape);
                    }
                } else {
                    if (character < 0x20) {
                        throw new IllegalArgumentException("control character in string");
                    }
                    value.append(character);
                }
            }
        }

        private String parseValue() {
            skip();
            char character = peek();
            if (character == '"') {
                return parseString();
            }
            if (character == '{' || character == '[') {
                throw new IllegalArgumentException("only flat objects are supported");
            }
            StringBuilder token = new StringBuilder();
            while (index < text.length()) {
                char current = text.charAt(index);
                if (current == ',' || current == '}' || Character.isWhitespace(current)) {
                    break;
                }
                token.append(current);
                index++;
            }
            String value = token.toString();
            if (value.isEmpty()) {
                throw new IllegalArgumentException("value is required");
            }
            if (value.equals("true") || value.equals("false") || value.equals("null")
                    || value.matches(NUMBER)) {
                return value;
            }
            throw new IllegalArgumentException("unsupported value: " + value);
        }

        private String read(int count) {
            if (index + count > text.length()) {
                throw new IllegalArgumentException("unexpected end of input");
            }
            String value = text.substring(index, index + count);
            if (!value.matches("[0-9A-Fa-f]+")) {
                throw new IllegalArgumentException("bad unicode escape");
            }
            index += count;
            return value;
        }

        private void skip() {
            while (index < text.length() && Character.isWhitespace(text.charAt(index))) {
                index++;
            }
        }

        private void expect(char expected) {
            if (next() != expected) {
                throw new IllegalArgumentException("expected " + expected);
            }
        }

        private char peek() {
            if (index >= text.length()) {
                throw new IllegalArgumentException("unexpected end of input");
            }
            return text.charAt(index);
        }

        private char next() {
            char value = peek();
            index++;
            return value;
        }
    }

    public static void main(String[] args) {
        System.out.println(parseFlatJson("{\"name\":\"Ada\",\"chapter\":39,\"active\":true}"));
        System.out.println(jsonEscape("line\n\"quoted\""));
        System.out.println(statusFamily(201));
    }
}
