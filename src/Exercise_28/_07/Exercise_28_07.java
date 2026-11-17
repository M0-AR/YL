package Exercise_28._07;

public class Exercise_28_07 {
    public static void main(String[] args) {
        int[][] triangle = {
            {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}
        };
        int[][] tree = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}
        };
        UnweightedGraph<Integer> cyclic = new UnweightedGraph<>(triangle, 3);
        System.out.println("Cycle from 0: " + cyclic.getACycle(0));
        System.out.println("Cycle from 1: " + cyclic.getACycle(1));
        System.out.println("Cycle from 0 in a tree: "
                + new UnweightedGraph<Integer>(tree, 3).getACycle(0));
    }
}
