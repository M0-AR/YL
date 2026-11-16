// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._09;

public class BinaryFormatException extends NumberFormatException {
	private String binaryString;


	public BinaryFormatException(String binaryString) {
		super(binaryString + " is not a binary string");
		this.binaryString = binaryString;
	}


	public String getBinaryString() {
		return binaryString;
	}
}
