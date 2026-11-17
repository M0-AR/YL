package Exercise_28._03;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V> {
    public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraphWithNonrecursiveDFS(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    @Override
    public AbstractGraph<V>.Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[vertices.size()];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (isVisited[u]) {
                continue;
            }
            isVisited[u] = true;
            searchOrder.add(u);
            List<Integer> adjacent = getNeighbors(u);
            for (int i = adjacent.size() - 1; i >= 0; i--) {
                int w = adjacent.get(i);
                if (!isVisited[w]) {
                    if (parent[w] == -1) {
                        parent[w] = u;
                    }
                    stack.push(w);
                }
            }
        }
        return new Tree(v, parent, searchOrder);
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1}, {0, 2}, {1, 0}, {1, 3}, {1, 4},
            {2, 0}, {2, 5}, {2, 6}, {3, 1}, {4, 1},
            {5, 2}, {6, 2}
        };
        UnweightedGraphWithNonrecursiveDFS<Integer> graph =
                new UnweightedGraphWithNonrecursiveDFS<>(edges, 7);
        System.out.println("DFS order from 0: " + graph.dfs(0).getSearchOrder());
        System.out.println("DFS order from 3: " + graph.dfs(3).getSearchOrder());
    }
}
