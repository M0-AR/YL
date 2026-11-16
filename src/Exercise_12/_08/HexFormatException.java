// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._08;

public class HexFormatException extends NumberFormatException {
	private String hex;


	public HexFormatException(String hex) {
		super(hex + " is not a hex string");
		this.hex = hex;
	}


	public String getHex() {
		return hex;
	}
}
