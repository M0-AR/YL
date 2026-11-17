package Exercise_05._06Extra;

import java.util.Scanner;

public class Exercise_05_06Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a genome string: ");
		String genome = input.nextLine();

		boolean found = false;
		int start = -1;
		for (int i = 0; i <= genome.length() - 3; i++) {
			String codon = genome.substring(i, i + 3);
			if (codon.equals("ATG")) {
				start = i + 3;
			}
			else if (start >= 0 && isStopCodon(codon)) {
				String gene = genome.substring(start, i);
				if (gene.length() % 3 == 0) {
					System.out.println(gene);
					found = true;
					start = -1;
				}
			}
		}

		if (!found) {
			System.out.println("no gene is found");
		}
	}

	private static boolean isStopCodon(String codon) {
		return codon.equals("TAG") || codon.equals("TAA") || codon.equals("TGA");
	}
}
