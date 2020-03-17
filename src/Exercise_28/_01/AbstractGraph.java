package Exercise_28._01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>(  ); // Store vertices
    protected List<List<Edge>> neighbors = new ArrayList<>(  ); // Adjacency lists
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public List<V> getVertices() {
        return null;
    }

    @Override
    public V getVertex(int index) {
        return null;
    }

    @Override
    public int getIndex(V v) {
        return 0;
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        return null;
    }

    @Override
    public int getDegree(int v) {
        return 0;
    }

    @Override
    public void printEdges() {

    }

    @Override
    public void clearGraph() {

    }

    @Override
    public boolean addVertex(V vertex) {
        return false;
    }

    @Override
    public boolean addEdge(int u, int v) {
        return false;
    }

    @Override
    public AbstractGraph<V>.Tree dfs(int v) {
        return null;
    }

    @Override
    public AbstractGraph<V>.Tree bfs(int v) {
        return null;
    }

    /** Tree inner class inside the AbstractGraph class*/
    public class Tree{
       private int root; // The root of th tree
       private int[] parent; // Store the parent of each vertex
       private List<Integer> searchOrder; // Store the search order

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int[] getParent() {
            return parent;
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        // Return the number of vertices
        public int getNumberOfVertices(){
            return searchOrder.size();
        }

        // Return the path of vertices from a vertex to the root
        public List<V> getPath(int index){
            ArrayList<V> path = new ArrayList<>(  );

            do {
                path.add( vertices.get( index ) );
                index = parent[index];
            }while (index != -1);

            return path;
        }

        // Todo from here
    }

    /** Edge inner class inside the AbstractGraph class */
    public static class Edge{
        public int u; // Starting vertex of the edge
        public int v; // Ending vertex of the edge

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object o){
            return u == ((Edge)o).u && v == ((Edge)o).v;
        }
    }
}
