package Exercise_12._05Extra;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise_12_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name for baby name ranking: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File " + filename + " does not exist");
			return;
		}

		File outputFile = new File(filename + ".new");
		try (Scanner fileInput = new Scanner(file);
				PrintWriter output = new PrintWriter(outputFile)) {
			while (fileInput.hasNext()) {
				fileInput.nextInt();
				output.println(fileInput.nextLine());
			}
		}
		catch (Exception ex) {
			System.out.println("Unable to process " + filename);
			return;
		}

		System.out.println("Done");
	}
}
