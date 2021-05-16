package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class ProductInformationClientImpl implements ProductInformationClient{

    @Value("${uri.productInformation}")
    private String productInformationUri;

    @Override
    public String getProductTitle() {
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create(productInformationUri)).build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (IOException e) {
            log.info("IOException:{}", e.getMessage());
        } catch (InterruptedException e) {
            log.info("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
