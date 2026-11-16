package Exercise_21._05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class Exercise_21_05 {
    private static final Set<String> KEYWORDS = Set.of(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while");

    private static final Set<String> LITERALS = Set.of("true", "false", "null");

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise_21_05 <input.java> <output.html>");
            return;
        }

        Path input = Path.of(args[0]);
        Path output = Path.of(args[1]);
        try {
            String source = Files.readString(input, StandardCharsets.UTF_8);
            Files.writeString(output, convert(source, input.getFileName().toString()),
                    StandardCharsets.UTF_8);
            System.out.println("Wrote " + output);
        } catch (IOException ex) {
            System.err.println("Unable to convert " + input + ": " + ex.getMessage());
            System.exit(1);
        }
    }

    static String convert(String source, String title) {
        String text = source.replace("\r\n", "\n").replace('\r', '\n');
        StringBuilder body = new StringBuilder(text.length() * 2);
        int i = 0;

        while (i < text.length()) {
            char current = text.charAt(i);

            if (current == '/' && i + 1 < text.length() && text.charAt(i + 1) == '/') {
                int start = i;
                i += 2;
                while (i < text.length() && text.charAt(i) != '\n') {
                    i++;
                }
                appendSpan(body, "comment", text.substring(start, i));
            } else if (current == '/' && i + 1 < text.length() && text.charAt(i + 1) == '*') {
                int start = i;
                i += 2;
                while (i + 1 < text.length()
                        && !(text.charAt(i) == '*' && text.charAt(i + 1) == '/')) {
                    i++;
                }
                i = Math.min(i + 2, text.length());
                appendSpan(body, "comment", text.substring(start, i));
            } else if (current == '"') {
                int start = i;
                if (i + 2 < text.length() && text.charAt(i + 1) == '"'
                        && text.charAt(i + 2) == '"') {
                    i += 3;
                    while (i + 2 < text.length()
                            && !(text.charAt(i) == '"' && text.charAt(i + 1) == '"'
                            && text.charAt(i + 2) == '"')) {
                        if (text.charAt(i) == '\\' && i + 1 < text.length()) {
                            i++;
                        }
                        i++;
                    }
                    i = Math.min(i + 3, text.length());
                } else {
                    i++;
                    while (i < text.length() && text.charAt(i) != '"') {
                        if (text.charAt(i) == '\\' && i + 1 < text.length()) {
                            i++;
                        }
                        i++;
                    }
                    i = Math.min(i + 1, text.length());
                }
                appendSpan(body, "literal", text.substring(start, i));
            } else if (current == '\'') {
                int start = i;
                i++;
                while (i < text.length() && text.charAt(i) != '\'') {
                    if (text.charAt(i) == '\\' && i + 1 < text.length()) {
                        i++;
                    }
                    i++;
                }
                i = Math.min(i + 1, text.length());
                appendSpan(body, "literal", text.substring(start, i));
            } else if (Character.isJavaIdentifierStart(current)) {
                int start = i;
                i++;
                while (i < text.length() && Character.isJavaIdentifierPart(text.charAt(i))) {
                    i++;
                }
                String word = text.substring(start, i);
                if (KEYWORDS.contains(word)) {
                    appendSpan(body, "keyword", word);
                } else if (LITERALS.contains(word)) {
                    appendSpan(body, "literal", word);
                } else {
                    body.append(escape(word));
                }
            } else if (Character.isDigit(current)) {
                int start = i;
                i++;
                while (i < text.length()) {
                    char next = text.charAt(i);
                    if (Character.isLetterOrDigit(next) || next == '_' || next == '.') {
                        i++;
                    } else {
                        break;
                    }
                }
                appendSpan(body, "literal", text.substring(start, i));
            } else {
                body.append(escape(String.valueOf(current)));
                i++;
            }
        }

        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>" + escape(title) + "</title>\n"
                + "<style>\n"
                + "body{background:#f8fafc;color:#1f2937;"
                + "font-family:system-ui,sans-serif;margin:2rem;}\n"
                + "pre{background:#fff;border:1px solid #cbd5e1;border-radius:8px;"
                + "padding:1.25rem;overflow:auto;line-height:1.5;tab-size:4;}\n"
                + ".keyword{color:navy;font-weight:bold;}\n"
                + ".comment{color:green;font-weight:bold;}\n"
                + ".literal{color:blue;font-weight:bold;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<pre><code>" + body + "</code></pre>\n"
                + "</body>\n"
                + "</html>\n";
    }

    private static void appendSpan(StringBuilder out, String cssClass, String text) {
        out.append("<span class=\"").append(cssClass).append("\">")
                .append(escape(text))
                .append("</span>");
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
