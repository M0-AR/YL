package Exercise_28._20Extra;

import java.util.ArrayList;
import java.util.List;

public class Exercise_28_20Extra {
	public static void main(String[] args) {
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}, {5, 6}};
		List<List<Integer>> components = components(7, edges);
		System.out.println(components);
		if (components.size() != 3 || components.get(0).size() != 3) {
			throw new IllegalStateException("Unexpected components");
		}
		System.out.println("Exercise_28_20Extra OK");
	}

	static List<List<Integer>> components(int count, int[][] edges) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		boolean[] visited = new boolean[count];
		List<List<Integer>> result = new ArrayList<>();
		for (int start = 0; start < count; start++) {
			if (!visited[start]) {
				List<Integer> component = new ArrayList<>();
				dfs(start, graph, visited, component);
				result.add(component);
			}
		}
		return result;
	}

	static void dfs(int node, List<List<Integer>> graph, boolean[] visited,
			List<Integer> component) {
		visited[node] = true;
		component.add(node);
		for (int next : graph.get(node)) {
			if (!visited[next]) {
				dfs(next, graph, visited, component);
			}
		}
	}
}
