package Exercise_25._18;

import Exercise_17._17.BitOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public final class HuffmanArchive {
    private static final int MAX_HEADER_BYTES = 16 * 1024 * 1024;

    private HuffmanArchive() {
    }

    public static void compress(Path source, Path target) throws IOException {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");
        byte[] data = Files.readAllBytes(source);
        int[] frequencies = new int[256];
        for (byte value : data) {
            frequencies[value & 0xff]++;
        }

        Node root = buildTree(frequencies);
        String[] codes = new String[256];
        if (root != null) {
            assignCodes(root, "", codes);
        }

        long bitCount = 0;
        for (int symbol = 0; symbol < frequencies.length; symbol++) {
            if (frequencies[symbol] > 0) {
                bitCount += (long) frequencies[symbol] * codes[symbol].length();
            }
        }

        Header header = new Header(codes, data.length, bitCount);
        byte[] headerBytes = serialize(header);

        try (DataOutputStream output = new DataOutputStream(
                new BufferedOutputStream(Files.newOutputStream(target)))) {
            output.writeInt(headerBytes.length);
            output.write(headerBytes);
            output.flush();

            BitOutputStream bits = new BitOutputStream(output);
            try {
                for (byte value : data) {
                    bits.writeBit(codes[value & 0xff]);
                }
            } finally {
                bits.close();
            }
        }
    }

    public static void decompress(Path source, Path target) throws IOException {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");

        try (DataInputStream input = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(source)))) {
            int headerLength = input.readInt();
            if (headerLength <= 0 || headerLength > MAX_HEADER_BYTES) {
                throw new IOException("Invalid archive header length: " + headerLength);
            }

            byte[] headerBytes = input.readNBytes(headerLength);
            if (headerBytes.length != headerLength) {
                throw new EOFException("Archive header is incomplete");
            }
            Header header = deserialize(headerBytes);
            validate(header);

            if (header.originalLength == 0) {
                Files.write(target, new byte[0]);
                return;
            }

            Node root = buildDecodingTree(header.codes);
            ByteArrayOutputStream output = new ByteArrayOutputStream(header.originalLength);
            BitInputStream bits = new BitInputStream(input);
            Node current = root;

            for (long i = 0; i < header.bitCount; i++) {
                int bit = bits.readBit();
                if (bit < 0) {
                    throw new EOFException("Compressed data is incomplete");
                }
                current = bit == 0 ? current.left : current.right;
                if (current == null) {
                    throw new IOException("Invalid Huffman bit sequence");
                }
                if (current.isLeaf()) {
                    if (current.symbol < 0) {
                        throw new IOException("Invalid Huffman decoding tree");
                    }
                    output.write(current.symbol);
                    current = root;
                }
            }

            if (current != root) {
                throw new IOException("Compressed data ends in the middle of a code");
            }
            byte[] result = output.toByteArray();
            if (result.length != header.originalLength) {
                throw new IOException("Decompressed length does not match the archive header");
            }
            Files.write(target, result);
        }
    }

    private static Node buildTree(int[] frequencies) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getWeight).thenComparingLong(Node::getOrder));
        long order = 0;
        for (int symbol = 0; symbol < frequencies.length; symbol++) {
            if (frequencies[symbol] > 0) {
                queue.add(new Node(symbol, frequencies[symbol], order++));
            }
        }
        if (queue.isEmpty()) {
            return null;
        }

        while (queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();
            queue.add(new Node(-1, left.weight + right.weight, order++, left, right));
        }
        return queue.remove();
    }

    private static void assignCodes(Node node, String prefix, String[] codes) {
        if (node.isLeaf()) {
            codes[node.symbol] = prefix.isEmpty() ? "0" : prefix;
            return;
        }
        assignCodes(node.left, prefix + "0", codes);
        assignCodes(node.right, prefix + "1", codes);
    }

    private static Node buildDecodingTree(String[] codes) throws IOException {
        Node root = new Node(-1, 0, 0);
        for (int symbol = 0; symbol < codes.length; symbol++) {
            String code = codes[symbol];
            if (code == null) {
                continue;
            }
            if (code.isEmpty()) {
                throw new IOException("Huffman code is empty");
            }
            Node current = root;
            for (int i = 0; i < code.length(); i++) {
                char bit = code.charAt(i);
                if (bit != '0' && bit != '1') {
                    throw new IOException("Huffman code contains a non-bit character");
                }
                if (current.symbol != -1) {
                    throw new IOException("Huffman code is not prefix-free");
                }
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new Node(-1, 0, 0);
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(-1, 0, 0);
                    }
                    current = current.right;
                }
            }
            if (!current.isLeaf() || current.symbol != -1) {
                throw new IOException("Huffman code is not prefix-free");
            }
            current.symbol = symbol;
        }
        return root;
    }

    private static void validate(Header header) throws IOException {
        if (header.codes == null || header.codes.length != 256
                || header.originalLength < 0 || header.bitCount < 0) {
            throw new IOException("Invalid Huffman archive header");
        }
    }

    private static byte[] serialize(Header header) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(header);
        }
        return bytes.toByteArray();
    }

    private static Header deserialize(byte[] bytes) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            Object object = input.readObject();
            if (!(object instanceof Header header)) {
                throw new IOException("Archive does not contain a Huffman header");
            }
            return header;
        } catch (ClassNotFoundException exception) {
            throw new IOException("Unable to read the Huffman header", exception);
        }
    }

    private static final class Header implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String[] codes;
        private final int originalLength;
        private final long bitCount;

        private Header(String[] codes, int originalLength, long bitCount) {
            this.codes = codes;
            this.originalLength = originalLength;
            this.bitCount = bitCount;
        }
    }

    private static final class Node {
        private int symbol;
        private final int weight;
        private final long order;
        private Node left;
        private Node right;

        private Node(int symbol, int weight, long order) {
            this(symbol, weight, order, null, null);
        }

        private Node(int symbol, int weight, long order, Node left, Node right) {
            this.symbol = symbol;
            this.weight = weight;
            this.order = order;
            this.left = left;
            this.right = right;
        }

        private int getWeight() {
            return weight;
        }

        private long getOrder() {
            return order;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
