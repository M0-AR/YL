package Exercise_25._16;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeMap;

public final class HuffmanTree {
    private final Node root;
    private final Map<Character, Integer> frequencies;
    private final Map<Character, String> codes;

    private HuffmanTree(Node root, Map<Character, Integer> frequencies, Map<Character, String> codes) {
        this.root = root;
        this.frequencies = Collections.unmodifiableMap(frequencies);
        this.codes = Collections.unmodifiableMap(codes);
    }

    public static HuffmanTree fromText(String text) {
        Objects.requireNonNull(text, "text");
        Map<Character, Integer> frequencies = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            frequencies.merge(text.charAt(i), 1, Integer::sum);
        }
        return fromFrequencies(frequencies);
    }

    public static HuffmanTree fromFrequencies(Map<Character, Integer> frequencies) {
        Objects.requireNonNull(frequencies, "frequencies");
        TreeMap<Character, Integer> copy = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            Character symbol = Objects.requireNonNull(entry.getKey(), "symbol");
            Integer count = Objects.requireNonNull(entry.getValue(), "frequency");
            if (count <= 0) {
                throw new IllegalArgumentException("frequency must be positive");
            }
            copy.put(symbol, count);
        }

        if (copy.isEmpty()) {
            return new HuffmanTree(null, copy, new TreeMap<>());
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getWeight).thenComparingLong(Node::getOrder));
        long order = 0;
        for (Map.Entry<Character, Integer> entry : copy.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue(), order++, null, null));
        }

        while (queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();
            queue.add(new Node(null, left.weight + right.weight, order++, left, right));
        }

        Node root = queue.remove();
        TreeMap<Character, String> codes = new TreeMap<>();
        assignCodes(root, "", codes);
        return new HuffmanTree(root, copy, codes);
    }

    private static void assignCodes(Node node, String prefix, Map<Character, String> codes) {
        if (node.isLeaf()) {
            codes.put(node.symbol, prefix.isEmpty() ? "0" : prefix);
            return;
        }
        assignCodes(node.left, prefix + "0", codes);
        assignCodes(node.right, prefix + "1", codes);
    }

    public Node getRoot() {
        return root;
    }

    public Map<Character, Integer> getFrequencies() {
        return frequencies;
    }

    public Map<Character, String> getCodes() {
        return codes;
    }

    public int getWeight() {
        return root == null ? 0 : root.weight;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public String encode(String text) {
        Objects.requireNonNull(text, "text");
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            String code = codes.get(symbol);
            if (code == null) {
                throw new IllegalArgumentException("No Huffman code for " + symbol);
            }
            bits.append(code);
        }
        return bits.toString();
    }

    public String decode(String bits) {
        Objects.requireNonNull(bits, "bits");
        if (bits.isEmpty()) {
            return "";
        }
        if (root == null) {
            throw new IllegalArgumentException("Cannot decode with an empty tree");
        }

        StringBuilder text = new StringBuilder();
        Node current = root;
        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);
            if (bit != '0' && bit != '1') {
                throw new IllegalArgumentException("Bit strings contain only 0 and 1");
            }
            if (root.isLeaf()) {
                if (bit != '0') {
                    throw new IllegalArgumentException("Invalid bit for a single-symbol tree");
                }
                text.append(root.symbol);
                continue;
            }
            current = bit == '0' ? current.left : current.right;
            if (current == null) {
                throw new IllegalArgumentException("Invalid Huffman bit sequence");
            }
            if (current.isLeaf()) {
                text.append(current.symbol);
                current = root;
            }
        }
        if (current != root) {
            throw new IllegalArgumentException("Incomplete Huffman bit sequence");
        }
        return text.toString();
    }

    public static final class Node {
        private final Character symbol;
        private final int weight;
        private final long order;
        private final Node left;
        private final Node right;

        private Node(Character symbol, int weight, long order, Node left, Node right) {
            this.symbol = symbol;
            this.weight = weight;
            this.order = order;
            this.left = left;
            this.right = right;
        }

        public Character getSymbol() {
            return symbol;
        }

        public int getWeight() {
            return weight;
        }

        public long getOrder() {
            return order;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
