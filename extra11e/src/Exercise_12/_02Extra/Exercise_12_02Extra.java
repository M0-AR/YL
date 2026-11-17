package Exercise_12._02Extra;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Exercise_12_02Extra {
	public static void main(String[] args) {
		TreeSet<String> names = new TreeSet<>();

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

		try (PrintWriter output =
				new PrintWriter("AllBabyNamesSortedDuplicatesNotAllowed.txt")) {
			int count = 0;
			for (String name : names) {
				output.print(name);
				count++;
				if (count % 10 == 0) {
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
