package com.Project.Store.repository;

import com.Project.Store.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);
}
