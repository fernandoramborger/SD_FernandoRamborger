package br.inatel.labs.labrest.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.inatel.labs.labrest.server.model.Product;

@Service
public class ProductService {

	private List<Product> products = new ArrayList<>();

	public List<Product> findAll() {
		return this.products;
	}

	@PostConstruct
	private void setup() {
		Product p1 = new Product(1L, "Furadeira", new BigDecimal(230.00));
		Product p2 = new Product(2L, "Serra Circular", new BigDecimal(500.00));
		Product p3 = new Product(3L, "Parafusadeira", new BigDecimal(400.00));
		products.add(p1);
		products.add(p2);
		products.add(p3);
	}

	public Optional<Product> findById(Long id) {
		return products.stream()
				.filter(p -> p.getId().equals(id))
				.findFirst();
	}

	public Product create(Product p) {
		Long idRandom = new Random().nextLong();
		p.setId(idRandom);
		products.add(p);
		return p;
	}

	public void update(Product p) {
		products.removeIf(product -> product.getId().equals(p.getId()));
		products.add(p);
	}

	public void remove(Product p) {
		products.removeIf(product -> product.getId().equals(p.getId()));
	}

	public List<Product> findByFragDescricao(String fragDescricao) {
		if (Objects.isNull(fragDescricao) || fragDescricao.isBlank()) {
			return List.of();
		}
		String fragDescricaoLowerCase = fragDescricao.trim().toLowerCase();
		return products.stream()
				.filter(p -> p.getDescription().toLowerCase().contains(fragDescricaoLowerCase))
				.toList();
	}
}
