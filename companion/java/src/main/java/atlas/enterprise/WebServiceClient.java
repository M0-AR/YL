package atlas.enterprise;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public final class WebServiceClient {
    private WebServiceClient() {
    }

    public static HttpResponse<String> getJson(String url) throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Accept", "application/json")
            .timeout(Duration.ofSeconds(10))
            .GET()
            .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws Exception {
        String url = args.length > 0
            ? args[0]
            : "https://api.github.com/repos/M0-AR/YL";
        HttpResponse<String> response = getJson(url);
        System.out.println("status = " + response.statusCode());
        System.out.println(response.body());
    }
}
