package Exercise_28._08;

public class Exercise_28_08 {
    public static void main(String[] args) {
        int[][] square = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 3}, {3, 2}, {3, 0}, {0, 3}
        };
        int[][] triangle = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {0, 2}
        };
        System.out.println("Square is bipartite: "
                + new UnweightedGraph<Integer>(square, 4).isBipartite());
        System.out.println("Triangle is bipartite: "
                + new UnweightedGraph<Integer>(triangle, 3).isBipartite());
    }
}
