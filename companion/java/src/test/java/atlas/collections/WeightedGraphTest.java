package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeightedGraphTest {
    @Test
    void findsShortestPathsThroughUndirectedEdges() {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 2, 1);
        graph.addUndirectedEdge(2, 1, 2);
        graph.addUndirectedEdge(1, 3, 5);
        graph.addUndirectedEdge(2, 3, 8);
        graph.addUndirectedEdge(3, 4, 3);

        assertArrayEquals(new int[]{0, 3, 1, 8, 11}, graph.dijkstra(0));
    }

    @Test
    void respectsEdgeDirection() {
        WeightedGraph graph = new WeightedGraph(3);
        graph.addDirectedEdge(0, 1, 2);
        graph.addDirectedEdge(0, 2, 9);
        graph.addDirectedEdge(1, 2, 3);

        assertArrayEquals(new int[]{0, 2, 5}, graph.dijkstra(0));
        assertArrayEquals(new int[]{Integer.MAX_VALUE, 0, 3}, graph.dijkstra(1));
    }

    @Test
    void marksUnreachableNodesAsInfinite() {
        WeightedGraph graph = new WeightedGraph(3);
        graph.addUndirectedEdge(0, 1, 7);

        int[] distances = graph.dijkstra(0);
        assertEquals(7, distances[1]);
        assertEquals(Integer.MAX_VALUE, distances[2]);
    }

    @Test
    void rejectsNegativeWeights() {
        WeightedGraph graph = new WeightedGraph(2);
        assertThrows(IllegalArgumentException.class, () -> graph.addDirectedEdge(0, 1, -1));
    }

    @Test
    void exposesEdgesWithoutAllowingMutation() {
        WeightedGraph graph = new WeightedGraph(2);
        graph.addUndirectedEdge(0, 1, 5);

        assertEquals(1, graph.edges(0).size());
        assertEquals(1, graph.edges(1).size());
        assertEquals(5, graph.edges(0).get(0).weight());
        assertThrows(UnsupportedOperationException.class, () -> graph.edges(0).clear());
    }

    @Test
    void mainExampleProducesExpectedDistances() {
        WeightedGraph graph = new WeightedGraph(6);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 2, 1);
        graph.addUndirectedEdge(2, 1, 2);
        graph.addUndirectedEdge(1, 3, 5);
        graph.addUndirectedEdge(2, 3, 8);
        graph.addUndirectedEdge(3, 4, 3);
        graph.addUndirectedEdge(4, 5, 2);

        assertEquals("[0, 3, 1, 8, 11, 13]", Arrays.toString(graph.dijkstra(0)));
    }
}
