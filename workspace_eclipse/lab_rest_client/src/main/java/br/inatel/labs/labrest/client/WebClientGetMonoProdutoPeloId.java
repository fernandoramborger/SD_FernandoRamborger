package br.inatel.labs.labrest.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.dto.ProductDTO;
import reactor.core.publisher.Mono;

public class WebClientGetMonoProductById {
    public static void main(String[] args) {

        try {
            Mono<ProductDTO> monoProduct = WebClient.create("http://localhost:54014")
                .get()
                .uri("/product/3")
                .retrieve()
                .bodyToMono(ProductDTO.class);

            monoProduct.subscribe();

            ProductDTO product = monoProduct.block();

            System.out.println("Product found:");
            System.out.println(product);

        } catch (WebClientResponseException e) {
            System.out.println("Response status: " + e.getStatusCode());
            System.out.println("Response body: " + e.getResponseBodyAsString());
        }
    }
}
