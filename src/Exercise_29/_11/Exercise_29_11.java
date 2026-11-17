package Exercise_29._11;

import Exercise_29._common.GraphFileReader;
import Exercise_29._common.WeightedGraph;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Exercise_29_11 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String fileName = input.nextLine().trim();
        System.out.print("Enter two vertices (integer indexes): ");
        int from = input.nextInt();
        int to = input.nextInt();

        WeightedGraph<Integer> graph = GraphFileReader.read(fileName);
        System.out.println("The number of vertices is " + graph.getSize());
        for (int u = 0; u < graph.getSize(); u++) {
            System.out.print("Vertex " + u + ": ");
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }

        WeightedGraph<Integer>.ShortestPathTree tree = graph.getShortestPath(from);
        List<Integer> path = tree.getPath(to);
        if (path == null) {
            System.out.println("There is no path from " + from + " to " + to);
        } else {
            System.out.print("A path from " + from + " to " + to + ": ");
            for (int vertex : path) {
                System.out.print(vertex + " ");
            }
            System.out.println();
            System.out.println("Total weight is " + tree.getCost(to));
        }
    }
}
