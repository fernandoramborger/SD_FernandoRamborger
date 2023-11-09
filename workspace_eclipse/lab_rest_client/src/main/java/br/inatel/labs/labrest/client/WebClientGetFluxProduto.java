package br.inatel.labs.labrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProductDTO;
import reactor.core.publisher.Flux;

public class WebClientGetFluxProduct {
    public static void main(String[] args) {

        List<ProductDTO> productList = new ArrayList<>();

        Flux<ProductDTO> fluxProduct = WebClient.create("http://localhost:8080")
            .get()
            .uri("/product")
            .retrieve()
            .bodyToFlux(ProductDTO.class);

        fluxProduct.subscribe(p -> productList.add(p));

        fluxProduct.blockLast();

        System.out.println("Product list:");
        System.out.println(productList);
    }
}
