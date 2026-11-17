package Exercise_29._05Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise_29_05Extra {
	record Edge(int u, int v, int weight) {
	}

	public static void main(String[] args) {
		int[][] weights = {
			{0, 2, 0, 6, 0},
			{2, 0, 3, 8, 5},
			{0, 3, 0, 0, 7},
			{6, 8, 0, 0, 9},
			{0, 5, 7, 9, 0}
		};
		List<Edge> mst = prim(weights);
		int total = mst.stream().mapToInt(Edge::weight).sum();
		System.out.println(mst);
		System.out.println("Total weight: " + total);
		if (mst.size() != 4 || total != 16) {
			throw new IllegalStateException("Unexpected MST");
		}
		System.out.println("Exercise_29_05Extra OK");
	}

	static List<Edge> prim(int[][] weights) {
		int n = weights.length;
		boolean[] inTree = new boolean[n];
		int[] best = new int[n];
		int[] parent = new int[n];
		Arrays.fill(best, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		best[0] = 0;
		List<Edge> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int u = -1;
			for (int j = 0; j < n; j++) {
				if (!inTree[j] && (u == -1 || best[j] < best[u])) {
					u = j;
				}
			}
			if (u == -1 || best[u] == Integer.MAX_VALUE) {
				break;
			}
			inTree[u] = true;
			if (parent[u] != -1) {
				result.add(new Edge(parent[u], u, best[u]));
			}
			for (int v = 0; v < n; v++) {
				if (!inTree[v] && weights[u][v] > 0 && weights[u][v] < best[v]) {
					best[v] = weights[u][v];
					parent[v] = u;
				}
			}
		}
		return result;
	}
}
