package Exercise_29._common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class GraphFileReader {
    private GraphFileReader() {
    }

    public static WeightedGraph<Integer> read(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName));
        int numberOfVertices = Integer.parseInt(lines.get(0).trim());
        List<WeightedGraph.WeightedEdge> edges = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            for (String triplet : line.split("\\|")) {
                String[] parts = triplet.trim().split(",");
                if (parts.length < 3) {
                    continue;
                }
                int u = Integer.parseInt(parts[0].trim());
                int v = Integer.parseInt(parts[1].trim());
                double weight = Double.parseDouble(parts[2].trim());
                edges.add(new WeightedGraph.WeightedEdge(u, v, weight));
                edges.add(new WeightedGraph.WeightedEdge(v, u, weight));
            }
        }
        return new WeightedGraph<>(edges, numberOfVertices);
    }
}
