package Exercise_28._17;

public class Exercise_28_17 {
    public static void main(String[] args) {
        int[][] cycle = {
            {0, 1}, {0, 2}, {0, 3},
            {1, 0}, {1, 2},
            {2, 0}, {2, 1}, {2, 3},
            {3, 0}, {3, 2}
        };
        int[][] tree = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}
        };
        Graph<Integer> graph = new UnweightedGraph<>(cycle, 4);
        System.out.println("Hamiltonian cycle: " + graph.getHamiltonianCycle());
        System.out.println("Hamiltonian cycle in a tree: "
                + new UnweightedGraph<Integer>(tree, 3).getHamiltonianCycle());
    }
}
