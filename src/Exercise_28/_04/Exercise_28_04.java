// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_28._04;

import java.util.*;

public class Exercise_28_04 {
	public static void main(String[] args) throws Exception {

		System.out.print("Enter file name: ");
		Scanner fileName = new Scanner(System.in);
		java.io.File file = new java.io.File(fileName.nextLine());


		if (!file.exists()) {
			System.out.println("The file \"" + file + "\" does not exist.");
			System.exit(0);
		}

		try (

			Scanner input = new Scanner(file);
		) {
			int NUMBER_OF_VERTICES = input.nextInt();


			ArrayList<AbstractGraph.Edge> edges = new ArrayList<>();

			while (input.hasNext()) {
				String s = input.nextLine();
				String[] line = s.split("[\\s+]");


				for (int j = 1; j < line.length; j++) {
					edges.add(new AbstractGraph.Edge(
						Integer.parseInt(line[0]), Integer.parseInt(line[j])));
				}
			}


			MyGraph<String> graph = new MyGraph<>(edges, NUMBER_OF_VERTICES);
			System.out.println(graph.getConnectedComponents());
		}
	}
}
