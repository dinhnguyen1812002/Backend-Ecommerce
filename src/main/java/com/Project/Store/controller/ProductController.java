package com.Project.Store.controller;

import com.Project.Store.entity.Product;
import com.Project.Store.repository.CategoryRepository;
import com.Project.Store.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping("")
    public Iterable<Product> findAll(){
        List<Product> products = new ArrayList<>();
        Iterator<Product> iterator = productRepository.findAll().iterator();
        iterator.forEachRemaining(products::add);
        Collections.reverse(products);
        return products;
    }
    @PostMapping("")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }

}
