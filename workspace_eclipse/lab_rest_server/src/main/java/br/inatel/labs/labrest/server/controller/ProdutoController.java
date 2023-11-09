package br.inatel.labs.labrest.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.labs.labrest.server.model.Product;
import br.inatel.labs.labrest.server.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping 
	public List<Product> getProducts() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Long productId) {
		Optional<Product> optionalProduct = service.findById(productId);
		
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		
		String msg = String.format("No product found with id [%s]", productId);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		Product createdProduct = service.create(product);
		return createdProduct;
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody Product product) {
		service.update(product);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") Long productId) {
		Product foundProduct = getProductById(productId);
		service.remove(foundProduct);
	}
	
	@GetMapping("/search")
	public List<Product> getByDescriptionFragment(@RequestParam("description") String descriptionFragment) {
		return service.findByDescriptionFragment(descriptionFragment);
	}
}
