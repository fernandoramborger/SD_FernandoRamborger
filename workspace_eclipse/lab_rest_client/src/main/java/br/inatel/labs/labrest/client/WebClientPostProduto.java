package br.inatel.labs.labrest.client;

import java.math.BigDecimal;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProductDTO;

public class WebClientPostProduct {
    public static void main(String[] args) {

        ProductDTO newProduct = new ProductDTO();
        newProduct.setDescription("Hammer");
        newProduct.setPrice(new BigDecimal(25.00));

        ProductDTO createdProduct = WebClient.create("http://localhost:8080")
            .post()
            .uri("/product")
            .bodyValue(newProduct)
            .retrieve()
            .bodyToMono(ProductDTO.class)
            .block();

        System.out.println("Product created:");
        System.out.println(createdProduct); // with filled ID
    }
}
