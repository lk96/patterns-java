package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class PriceClientImpl implements PriceClient {

    @Override
    public String getPrice() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:9002/price"))
                .build();
        try {
            log.info("Sending request to fetch price info");
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logResponse(response);
            return response.body();
        }catch (IOException ioe) {
            log.error("Failure occurred while getting image path", ioe);
        } catch (InterruptedException ie) {
            log.error("Failure occurred while getting image path", ie);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    private void logResponse(HttpResponse<String> response) {
        if (isSuccessResponse(response.statusCode())) {
            log.info("price info received successfully");
        }else {
            log.error("price info request failed");
        }
    }

    private boolean isSuccessResponse(int responseCode){
        return responseCode >= 200 && responseCode <= 299;
    }
}
