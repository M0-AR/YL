package Exercise_29._09;

import Exercise_29._common.GraphFileReader;
import Exercise_29._common.WeightedGraph;

import java.io.IOException;
import java.util.Scanner;

public class Exercise_29_09 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String fileName = input.nextLine().trim();
        WeightedGraph<Integer> graph = GraphFileReader.read(fileName);

        System.out.println("The number of vertices is " + graph.getSize());
        for (int u = 0; u < graph.getSize(); u++) {
            System.out.print("Vertex " + u + ": ");
            for (WeightedGraph.WeightedEdge edge : graph.getNeighbors(u)) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }

        WeightedGraph<Integer>.MST mst = graph.getMinimumSpanningTree();
        System.out.println("Total weight is " + mst.getTotalWeight());
        mst.printTree();
    }
}
