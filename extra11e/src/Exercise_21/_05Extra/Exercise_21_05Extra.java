package Exercise_21._05Extra;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Exercise_21_05Extra {
	private static final Set<String> KEYWORDS = Set.of(
			"abstract", "assert", "boolean", "break", "byte", "case", "catch",
			"char", "class", "const", "continue", "default", "do", "double",
			"else", "enum", "extends", "final", "finally", "float", "for",
			"goto", "if", "implements", "import", "instanceof", "int",
			"interface", "long", "native", "new", "package", "private",
			"protected", "public", "return", "short", "static", "strictfp",
			"super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while");

	public static void main(String[] args) {
		String source = """
				class Demo {
				    // int ignored = 1;
				    String text = "class if for";
				    public static void main(String[] args) {
				        if (args.length > 0) {
				            System.out.println(text);
				        }
				    }
				}
				""";
		Map<String, Integer> counts = countKeywords(source);
		System.out.println(counts);
		if (counts.getOrDefault("int", 0) != 0) {
			throw new IllegalStateException("Comment was counted");
		}
		if (counts.getOrDefault("if", 0) != 1) {
			throw new IllegalStateException("Unexpected if count");
		}
		if (counts.getOrDefault("public", 0) != 1) {
			throw new IllegalStateException("Unexpected public count");
		}
		System.out.println("Exercise_21_05Extra OK");
	}

	static Map<String, Integer> countKeywords(String source) {
		Map<String, Integer> counts = new TreeMap<>();
		int i = 0;
		while (i < source.length()) {
			char ch = source.charAt(i);
			if (ch == '/' && i + 1 < source.length() && source.charAt(i + 1) == '/') {
				i += 2;
				while (i < source.length() && source.charAt(i) != '\n') {
					i++;
				}
			}
			else if (ch == '/' && i + 1 < source.length() && source.charAt(i + 1) == '*') {
				i += 2;
				while (i + 1 < source.length()
						&& !(source.charAt(i) == '*' && source.charAt(i + 1) == '/')) {
					i++;
				}
				i = Math.min(source.length(), i + 2);
			}
			else if (ch == '"' || ch == '\'') {
				char quote = ch;
				i++;
				while (i < source.length()) {
					char current = source.charAt(i++);
					if (current == '\\' && i < source.length()) {
						i++;
					}
					else if (current == quote) {
						break;
					}
				}
			}
			else if (Character.isJavaIdentifierStart(ch)) {
				int start = i++;
				while (i < source.length() && Character.isJavaIdentifierPart(source.charAt(i))) {
					i++;
				}
				String word = source.substring(start, i);
				if (KEYWORDS.contains(word)) {
					counts.merge(word, 1, Integer::sum);
				}
			}
			else {
				i++;
			}
		}
		return counts;
	}
}
