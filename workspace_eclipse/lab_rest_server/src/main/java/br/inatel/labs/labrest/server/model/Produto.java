package br.inatel.labs.labrest.server.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
	private Long id;
	private String description;
	private BigDecimal price;

	public Product(Long id, String description, BigDecimal price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Product() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public
