// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_28._04;

import java.util.*;

public class MyGraph<V> extends UnweightedGraph<V> {

	public MyGraph() {
	}


	public MyGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}


	public MyGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}


	public MyGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}


	public MyGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}


	public List<List<Integer>> getConnectedComponents() {
		List<List<Integer>> components = new ArrayList<>();
		List<Integer> v = new ArrayList<>(vertices.size());
		for (int i = 0; i < vertices.size(); i++)
			v.add(i);

		getConnectedComponents(v, components);
		return components;
	}


	public void getConnectedComponents(
			List<Integer> v, List<List<Integer>> components) {
		if (v.size() > 0) {
			List<Integer> c = dfs(v.get(0)).getSearchOrder();
			components.add(c);
			v.removeAll(c);
			getConnectedComponents(v, components);
		}
	}
}
