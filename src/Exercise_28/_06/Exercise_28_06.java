package Exercise_28._06;

public class Exercise_28_06 {
    public static void main(String[] args) {
        int[][] triangle = {
            {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1},
            {0, 3}, {3, 0}
        };
        int[][] tree = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 3}, {3, 2}
        };
        System.out.println("Triangle with a leaf is cyclic: "
                + new UnweightedGraph<Integer>(triangle, 4).isCyclic());
        System.out.println("Tree is cyclic: "
                + new UnweightedGraph<Integer>(tree, 4).isCyclic());
    }
}
