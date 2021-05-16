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
public class ProductInventoryClientImpl implements ProductInventoryClient{

    @Value("${uri.productInventory}")
    private String productInventoryUri;

    @Override
    public Integer getInventories() {
        String response = "";
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create(productInventoryUri)).build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            response = httpResponse.body();
        } catch (IOException e) {
            log.info("IOException:{}",e.getMessage());
        } catch (InterruptedException e) {
            log.info("InterruptedException:{}",e.getMessage());
            Thread.currentThread().interrupt();
        }
        if ("".equalsIgnoreCase(response)) {
            return null;
        }else {
            return Integer.parseInt(response);
        }
    }
}
