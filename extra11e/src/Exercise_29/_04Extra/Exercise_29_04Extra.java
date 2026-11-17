package Exercise_29._04Extra;

import java.util.Arrays;

public class Exercise_29_04Extra {
	public static void main(String[] args) {
		int[][] weights = {
			{0, 2, 0, 6, 0},
			{2, 0, 3, 8, 5},
			{0, 3, 0, 0, 7},
			{6, 8, 0, 0, 9},
			{0, 5, 7, 9, 0}
		};
		int[] distance = dijkstra(weights, 0);
		System.out.println(Arrays.toString(distance));
		if (!Arrays.equals(distance, new int[] {0, 2, 5, 6, 7})) {
			throw new IllegalStateException("Unexpected distances");
		}
		System.out.println("Exercise_29_04Extra OK");
	}

	static int[] dijkstra(int[][] weights, int source) {
		int n = weights.length;
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] visited = new boolean[n];
		distance[source] = 0;
		for (int i = 0; i < n; i++) {
			int u = -1;
			for (int j = 0; j < n; j++) {
				if (!visited[j] && (u == -1 || distance[j] < distance[u])) {
					u = j;
				}
			}
			if (u == -1 || distance[u] == Integer.MAX_VALUE) {
				break;
			}
			visited[u] = true;
			for (int v = 0; v < n; v++) {
				if (!visited[v] && weights[u][v] > 0
						&& distance[u] + weights[u][v] < distance[v]) {
					distance[v] = distance[u] + weights[u][v];
				}
			}
		}
		return distance;
	}
}
