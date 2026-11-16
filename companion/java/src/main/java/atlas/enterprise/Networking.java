package atlas.enterprise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class Networking {
    private Networking() {
    }

    public static void main(String[] args) throws Exception {
        ExecutorService clients = Executors.newFixedThreadPool(2);
        try (ServerSocket server = new ServerSocket(0)) {
            server.setSoTimeout(5_000);
            int port = server.getLocalPort();

            clients.submit(() -> {
                try (Socket connection = server.accept()) {
                    handle(connection);
                } catch (IOException error) {
                    System.err.println("server: " + error.getMessage());
                }
            });

            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("127.0.0.1", port), 2_000);
                socket.setSoTimeout(2_000);

                try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), StandardCharsets.UTF_8));
                     BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8))) {

                    out.write("hello over tcp");
                    out.newLine();
                    out.flush();
                    System.out.println(in.readLine());
                }
            }
        } finally {
            clients.shutdown();
            clients.awaitTermination(3, TimeUnit.SECONDS);
        }
    }

    private static void handle(Socket connection) throws IOException {
        try (connection;
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 connection.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                 connection.getOutputStream(), StandardCharsets.UTF_8))) {

            String line = in.readLine();
            if (line != null) {
                out.write("echo: " + line);
                out.newLine();
                out.flush();
            }
        }
    }
}
