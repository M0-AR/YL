package Exercise_12._08Extra;

public class Exercise_12_08Extra {
	static class OctalFormatException extends NumberFormatException {
		private static final long serialVersionUID = 1L;

		OctalFormatException(String value) {
			super(value + " is not an octal string");
		}
	}

	public static void main(String[] args) {
		check("17", 15);
		check("777", 511);
		try {
			parseOctal("19");
			throw new IllegalStateException("Expected OctalFormatException");
		}
		catch (OctalFormatException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Exercise_12_08Extra OK");
	}

	static void check(String text, int expected) {
		int value = parseOctal(text);
		if (value != expected) {
			throw new IllegalStateException(text + " -> " + value);
		}
		System.out.println(text + " -> " + value);
	}

	static int parseOctal(String text) {
		if (text == null || text.isEmpty()) {
			throw new OctalFormatException(String.valueOf(text));
		}
		int value = 0;
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (ch < '0' || ch > '7') {
				throw new OctalFormatException(text);
			}
			value = value * 8 + ch - '0';
		}
		return value;
	}
}
