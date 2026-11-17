package Exercise_12._03Extra;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise_12_03Extra {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<>();

		for (int year = 2001; year <= 2010; year++) {
			File file = new File("babynamesranking" + year + ".txt");
			try (Scanner input = new Scanner(file)) {
				while (input.hasNext()) {
					input.nextInt();
					names.add(input.next());
					input.nextInt();
					names.add(input.next());
					input.nextInt();
				}
			}
			catch (Exception ex) {
				System.out.println("Unable to read " + file.getName());
				return;
			}
		}

		Collections.sort(names);

		try (PrintWriter output =
				new PrintWriter("AllBabyNamesSortedAllowDuplicates.txt")) {
			for (int i = 0; i < names.size(); i++) {
				output.print(names.get(i));
				if ((i + 1) % 10 == 0) {
					output.println();
				}
				else {
					output.print(" ");
				}
			}
		}
		catch (Exception ex) {
			System.out.println("Unable to write the output file");
			return;
		}

		System.out.println("Done");
	}
}
