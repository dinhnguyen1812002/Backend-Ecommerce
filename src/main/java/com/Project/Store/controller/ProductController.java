package com.Project.Store.controller;

import com.Project.Store.entity.Category;
import com.Project.Store.entity.Product;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.CategoryRepository;
import com.Project.Store.repository.ProductRepository;
import com.Project.Store.services.CategoryServices;
import com.Project.Store.services.ProductService;
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
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryServices categoryServices;
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping
    public Iterable<Product> findAll(){
        List<Product> products = new ArrayList<>();
        Iterator<Product> iterator = productRepository.findAll().iterator();
        iterator.forEachRemaining(products::add);
        Collections.reverse(products);
        return products;
    }


    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product,Long categoryId) {
        Category cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));

        product.setCategory(cat);
        Product newProduct = productRepository.save(product);

        return ResponseEntity.ok(newProduct);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Product product) {
        Product oldProduct = productService.findById(id);
        if(oldProduct == null) {
            return ResponseEntity.notFound().build();
        }
        oldProduct.setTitle(product.getTitle());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImage(product.getImage());

        Product updProduct = productRepository.save(oldProduct);
        return ResponseEntity.ok(updProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCategory(@PathVariable Long id)
    {
        Product deleteProduct = productRepository.findById(id).
                orElseThrow(() -> new NotFoundException("not found: "+id));
        productService.deleteProdct(id);
        return ResponseEntity.ok(deleteProduct);
    }
}
