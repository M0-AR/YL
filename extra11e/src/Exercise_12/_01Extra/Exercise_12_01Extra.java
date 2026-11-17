package Exercise_12._01Extra;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise_12_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name for baby name ranking: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File " + filename + " does not exist");
			return;
		}

		ArrayList<String> boyNames = new ArrayList<>();
		ArrayList<String> girlNames = new ArrayList<>();

		try (Scanner fileInput = new Scanner(file)) {
			while (fileInput.hasNext()) {
				fileInput.nextInt();
				boyNames.add(fileInput.next());
				fileInput.nextInt();
				girlNames.add(fileInput.next());
				fileInput.nextInt();
			}
		}
		catch (IOException ex) {
			System.out.println("Error reading " + filename);
			return;
		}

		boyNames.retainAll(girlNames);
		System.out.println(boyNames.size() + " names used for both genders");
		System.out.print("They are ");
		for (String name : boyNames) {
			System.out.print(name + " ");
		}
		System.out.println();
	}
}
