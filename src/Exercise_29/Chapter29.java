package Exercise_29;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class Chapter29 {
    public record Edge(String from, String to, int weight) {
        public Edge {
            if (from == null || from.isBlank() || to == null || to.isBlank()) {
                throw new IllegalArgumentException("both endpoints are required");
            }
            if (weight < 0) {
                throw new IllegalArgumentException("weight must be non-negative");
            }
            from = from.trim();
            to = to.trim();
        }
    }

    public static Edge parseEdge(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text is required");
        }
        String[] halves = text.split("->", -1);
        if (halves.length != 2) {
            throw new IllegalArgumentException("expected from->to:weight");
        }
        String[] tail = halves[1].split(":", -1);
        if (tail.length != 2) {
            throw new IllegalArgumentException("expected from->to:weight");
        }
        int weight = Integer.parseInt(tail[1].trim());
        return new Edge(halves[0].trim(), tail[0].trim(), weight);
    }

    public static Map<String, Integer> distances(List<Edge> edges, String source) {
        Map<String, List<Edge>> graph = buildGraph(edges);
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("source is required");
        }
        if (!graph.containsKey(source) && !hasIncoming(edges, source)) {
            throw new IllegalArgumentException("unknown source: " + source);
        }
        Map<String, Integer> best = new HashMap<>();
        PriorityQueue<Step> queue = new PriorityQueue<>(
                Comparator.comparingInt(Step::distance).thenComparing(Step::node));
        best.put(source, 0);
        queue.add(new Step(source, 0));
        while (!queue.isEmpty()) {
            Step step = queue.poll();
            if (step.distance() > best.getOrDefault(step.node(), Integer.MAX_VALUE)) {
                continue;
            }
            for (Edge edge : graph.getOrDefault(step.node(), List.of())) {
                int candidate = step.distance() + edge.weight();
                if (candidate < best.getOrDefault(edge.to(), Integer.MAX_VALUE)) {
                    best.put(edge.to(), candidate);
                    queue.add(new Step(edge.to(), candidate));
                }
            }
        }
        return new TreeMap<>(best);
    }

    public static List<String> shortestPath(List<Edge> edges, String from, String to) {
        Map<String, List<Edge>> graph = buildGraph(edges);
        if (from == null || to == null || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("both endpoints are required");
        }
        if (!graph.containsKey(from) && !hasIncoming(edges, from)) {
            throw new IllegalArgumentException("unknown start: " + from);
        }
        if (!graph.containsKey(to) && !hasIncoming(edges, to)) {
            throw new IllegalArgumentException("unknown goal: " + to);
        }
        Map<String, Integer> best = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Step> queue = new PriorityQueue<>(
                Comparator.comparingInt(Step::distance).thenComparing(Step::node));
        best.put(from, 0);
        queue.add(new Step(from, 0));
        while (!queue.isEmpty()) {
            Step step = queue.poll();
            if (step.distance() > best.getOrDefault(step.node(), Integer.MAX_VALUE)) {
                continue;
            }
            for (Edge edge : graph.getOrDefault(step.node(), List.of())) {
                int candidate = step.distance() + edge.weight();
                if (candidate < best.getOrDefault(edge.to(), Integer.MAX_VALUE)) {
                    best.put(edge.to(), candidate);
                    previous.put(edge.to(), step.node());
                    queue.add(new Step(edge.to(), candidate));
                }
            }
        }
        if (!best.containsKey(to)) {
            return List.of();
        }
        LinkedList<String> path = new LinkedList<>();
        String cursor = to;
        path.addFirst(cursor);
        while (!cursor.equals(from)) {
            cursor = previous.get(cursor);
            if (cursor == null) {
                return List.of();
            }
            path.addFirst(cursor);
        }
        return List.copyOf(path);
    }

    private static Map<String, List<Edge>> buildGraph(List<Edge> edges) {
        if (edges == null) {
            throw new IllegalArgumentException("edges are required");
        }
        Map<String, List<Edge>> graph = new HashMap<>();
        for (Edge edge : edges) {
            graph.computeIfAbsent(edge.from(), name -> new ArrayList<>()).add(edge);
        }
        return graph;
    }

    private static boolean hasIncoming(List<Edge> edges, String node) {
        Set<String> names = new HashSet<>();
        for (Edge edge : edges) {
            names.add(edge.from());
            names.add(edge.to());
        }
        return names.contains(node);
    }

    private record Step(String node, int distance) {
    }

    public static void main(String[] args) {
        List<Edge> edges = List.of(
                parseEdge("A->B:4"),
                parseEdge("A->C:1"),
                parseEdge("C->B:2"),
                parseEdge("B->D:5"),
                parseEdge("C->D:9"));
        System.out.println(distances(edges, "A"));
        System.out.println(shortestPath(edges, "A", "D"));
        System.out.println(shortestPath(edges, "D", "A"));
    }
}
