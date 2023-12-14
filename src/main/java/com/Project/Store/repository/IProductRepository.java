package com.Project.Store.repository;

import com.Project.Store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);
    @Query("SELECT p FROM Product p WHERE p.title LIKE CONCAT('%',:query, '%')")
    LinkedList<Product> searcProducts (String query);


}
