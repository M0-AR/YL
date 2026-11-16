// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_17._08;

import java.io.*;

public class Exercise_17_08 {
	public static void main(String[] args) throws IOException {
		try (
			RandomAccessFile inout =
				new RandomAccessFile("Exercise17_08.dat", "rw");
		) {

			if (inout.length() == 0) {
				inout.writeInt(1);
			}
			else {
				int count = inout.readInt();
				inout.seek(0);
				inout.writeInt(++count);
			}
		}
	}
}
