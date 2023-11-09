package br.inatel.labs.labrest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProductDTO;

public class WebClientPutProduct {
    
    public static void main(String[] args) {
        ProductDTO existingProduct = new ProductDTO();
        existingProduct.setId(1L);
        existingProduct.setDescription("Cordless Drill");

        ResponseEntity<Void> responseEntity = WebClient.create("http://localhost:54014")
            .put()
            .uri("/product")
            .bodyValue(existingProduct)
            .retrieve()
            .toBodilessEntity()
            .block();

        HttpStatus statusCode = responseEntity.getStatusCode();

        System.out.println("Product updated:");
        System.out.println("Response status: " + statusCode);
    }
}
