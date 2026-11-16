// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_17._12;

import java.io.*;

public class Exercise_17_12 {
	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println(
				"Usage: java Exercise_17_12 SourceFile1 . . . SourceFilen TargetFile");
			System.exit(0);
		}


		RandomAccessFile[] source = new RandomAccessFile[args.length - 1];

		try (
			RandomAccessFile target =
				new RandomAccessFile(args[args.length - 1], "rw");
		) {

			for (int i = 0; i < source.length; i++) {
				source[i] = new RandomAccessFile(args[i], "r");
			}



			for (int i = 0; i < source.length; i++) {
				byte[] b = new byte[(int)source[i].length()];
				source[i].seek(0);
				source[i].read(b);
				target.seek(target.length());
				target.write(b);
			}
		}
	}
}
