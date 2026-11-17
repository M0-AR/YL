package Exercise_18._01Extra;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Exercise_18_01Extra {
	public static void main(String[] args) {
		String directory;
		if (args.length > 0) {
			directory = args[0];
		}
		else {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a directory: ");
			directory = input.nextLine().trim();
		}

		File file = new File(directory);
		File largest = findLargestFile(file);
		if (largest == null) {
			System.out.println("No files found");
		}
		else {
			System.out.println("The largest file is "
				+ largest.getAbsolutePath());
			System.out.println("Size is " + largest.length() + " bytes");
			System.out.println("Last modified on "
				+ new Date(largest.lastModified()));
		}
	}

	public static File findLargestFile(File file) {
		if (file == null || !file.exists()) {
			return null;
		}
		if (file.isFile()) {
			return file;
		}

		File[] children = file.listFiles();
		if (children == null) {
			return null;
		}

		File largest = null;
		for (File child : children) {
			File candidate = findLargestFile(child);
			if (candidate != null
					&& (largest == null
						|| candidate.length() > largest.length())) {
				largest = candidate;
			}
		}
		return largest;
	}
}
