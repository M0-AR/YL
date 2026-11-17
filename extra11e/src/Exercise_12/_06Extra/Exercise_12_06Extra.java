package Exercise_12._06Extra;

import java.io.File;
import java.util.Date;

public class Exercise_12_06Extra {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise_12_06Extra filename");
			return;
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("The file does not exist");
			return;
		}

		File renamed = new File(file.getAbsolutePath()
			+ new Date().toString().replace(':', '_'));
		if (file.renameTo(renamed)) {
			System.out.println("Success. New file is " + renamed.getAbsolutePath());
		}
		else {
			System.out.println("Failed");
		}
	}
}
