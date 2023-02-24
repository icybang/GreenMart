package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Category;
import com.app.pojos.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findByCategory(Category category);

	List<Product> findByRate(double rate);

	List<Product> findByProductName(String productname);
}
