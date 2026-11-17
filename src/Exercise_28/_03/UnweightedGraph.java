// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_28._03;

import java.util.*;

public class UnweightedGraph<V> extends AbstractGraph {

	public UnweightedGraph() {
	}


	public UnweightedGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}


	public UnweightedGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}


	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}


	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}
}
