package com.essoft.JavaSpringPracticum.repositories;

import com.essoft.JavaSpringPracticum.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
