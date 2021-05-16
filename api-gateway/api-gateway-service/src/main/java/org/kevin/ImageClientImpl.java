package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class ImageClientImpl implements ImageClient {
    @Override
    public String getImagePath() {
        var httpClient = HttpClient.newHttpClient();
        var httpGet = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:9001/imagePath"))
                .build();

        try {
            log.info("Sending request to fetch image path");
            var httpResponse = httpClient.send(httpGet, HttpResponse.BodyHandlers.ofString());
            logResponse(httpResponse);
            return httpResponse.body();
        } catch (IOException ioe) {
            log.error("Failure occurred while getting image path", ioe);
        } catch (InterruptedException ie) {
            log.error("Failure occurred while getting image path", ie);
            Thread.currentThread().interrupt();
        }

        return null;
    }

    private void logResponse(HttpResponse<String> httpResponse) {
        if (isSuccessResponse(httpResponse.statusCode())) {
            log.info("Image path received successfully");
        } else {
            log.warn("Image path request failed");
        }
    }

    private boolean isSuccessResponse(int responseCode) {
        return responseCode >= 200 && responseCode <= 299;
    }
}
