package Exercise_28._01;

public interface Graph<V> {
    /** Returns the number of vertices in the graph */
    int getSize();

    /** Returns the vertices in the graph */
    java.util.List<V> getVertices();

    /** Returns the object for the specified vertex object */
    V getVertex(int index);

    /** Returns the index for the specified vertex object */
    int getIndex(V v);

    /** Return the neighbors of vertex with the specified index */
    java.util.List<Integer> getNeighbors(int index);

    /** Return the degree for a specified vertex */
    int getDegree(int v);

    /** Print the edges */
    void printEdges();

    /** Clears the graph */
    void clearGraph();

    /** Add vertex to the graph */
    boolean addVertex(V vertex);

    /** Add an edge to the graph */
    boolean addEdge(int u, int v);

    /** Obtains a depth-first search tree starting from v */
//    AbstractGraph<V>.Tree dfs(int v); todo

    /** Obtains a breadth-first search tree starting from v */
//    AbstractGraph<V>.Tree bfs(int v); todo

}
