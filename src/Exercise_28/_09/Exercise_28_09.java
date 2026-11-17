package Exercise_28._09;

public class Exercise_28_09 {
    public static void main(String[] args) {
        int[][] square = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 3}, {3, 2}, {3, 0}, {0, 3}
        };
        int[][] triangle = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {0, 2}
        };
        System.out.println("Bipartite sets: "
                + new UnweightedGraph<Integer>(square, 4).getBipartite());
        System.out.println("Triangle sets: "
                + new UnweightedGraph<Integer>(triangle, 3).getBipartite());
    }
}
