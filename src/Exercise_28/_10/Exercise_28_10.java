package Exercise_28._10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_28_10 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String fileName = input.nextLine().trim();
        System.out.print("Enter two vertices (integer indexes): ");
        int from = input.nextInt();
        int to = input.nextInt();

        List<String> lines = Files.readAllLines(Path.of(fileName));
        int numberOfVertices = Integer.parseInt(lines.get(0).trim());
        List<int[]> edgeList = new ArrayList<>();
        for (int i = 1; i <= numberOfVertices; i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            int u = Integer.parseInt(tokens[0]);
            for (int j = 1; j < tokens.length; j++) {
                int v = Integer.parseInt(tokens[j]);
                edgeList.add(new int[] {u, v});
                edgeList.add(new int[] {v, u});
            }
        }
        int[][] edges = edgeList.toArray(new int[0][]);
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, numberOfVertices);

        System.out.println("The number of vertices is " + graph.getSize());
        for (int i = 0; i < graph.getSize(); i++) {
            System.out.print("Vertex " + i + ": ");
            for (int neighbor : (List<Integer>) graph.getNeighbors(i)) {
                System.out.print("(" + i + ", " + neighbor + ") ");
            }
            System.out.println();
        }

        List<Integer> path = graph.getPath(from, to);
        if (path == null) {
            System.out.println("There is no path from " + from + " to " + to);
        } else {
            System.out.print("The path is ");
            for (int vertex : path) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }
}
