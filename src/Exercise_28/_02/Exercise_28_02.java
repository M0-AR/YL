// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_28._02;

public class Exercise_28_02 {
	public static void main(String[] args) throws Exception {
		final int NUMBER_OF_VERTICES = 12;


		int[][] edges = {
			{0, 1}, {0, 3}, {0, 5},
			{1, 0}, {1, 2}, {1, 3},
			{2, 1}, {2, 3}, {2, 4}, {2, 10},
			{3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
			{4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
			{5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
			{6, 5}, {6, 7},
			{7, 4}, {7, 5}, {7, 6}, {7, 8},
			{8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
			{9, 8}, {9, 11},
			{10, 2}, {10, 4}, {10, 8}, {10, 11},
			{11, 8}, {11, 9}, {11, 10}
		};


		Graph<Integer> graph = new UnweightedGraph<>(edges, NUMBER_OF_VERTICES);
		java.io.File file = new java.io.File("GraphFile.txt");
		if (file.exists()) {
			System.out.print("File already exists");
			System.exit(0);
		}

		try (

			java.io.PrintWriter output = new java.io.PrintWriter(file);
		) {

			output.println(graph.getSize());
			for (int i = 0; i < graph.getSize(); i++) {
				output.print(graph.getVertex(i) + " ");
				for (Integer e : graph.getNeighbors(i)) {
					output.print(e + " ");
				}
				output.println();
			}
		}
	}
}
