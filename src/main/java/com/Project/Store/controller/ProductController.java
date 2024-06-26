package com.Project.Store.controller;

import com.Project.Store.entity.Category;
import com.Project.Store.entity.Product;
import com.Project.Store.exception.CustomErrorException;

import com.Project.Store.exception.NotFoundException;
import com.Project.Store.services.CategoryServices;
import com.Project.Store.services.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryServices categoryServices;

    @Autowired
    public ProductController(ProductService productService){
        this.productService= productService;
    }
    @GetMapping
    public Iterable<Product> findAll(){
        List<Product> products = new LinkedList<>();
        Iterator<Product> iterator = productService.findAll();
        iterator.forEachRemaining(products::add);
        Collections.reverse(products);
        return products;
    }
    @GetMapping("/{id}")

    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id){
            Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer-Authentication")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        try {
            Category cat = categoryServices.getCategoryById(product.getCategory().getId());
            product.setCategory(cat);
            Product newProduct = productService.save(product);
            return ResponseEntity.ok(newProduct);
        }catch (NullPointerException e){
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }
    @PostMapping("/add")
    @SecurityRequirement(name = "Bearer-Authentication")
    public ResponseEntity<List<Product>> createProduct(@Valid @RequestBody List<Product> products) {
        try {
            List<Product> createdProducts = new ArrayList<>();
            for (Product product : products) {
                Category cat = categoryServices.getCategoryById(product.getCategory().getId());
                product.setCategory(cat);
                Product newProduct = productService.save(product);
                createdProducts.add(newProduct);
            }
            return ResponseEntity.ok(createdProducts);
        } catch (NullPointerException e) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer-Authentication")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Product product) {
        Product oldProduct = productService.findById(id);
        if(oldProduct == null) {
            return ResponseEntity.notFound().build();
        }
        oldProduct.setTitle(product.getTitle());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImage(product.getImage());
        oldProduct.setCategory(product.getCategory());
        Product updProduct = productService.save(oldProduct);
        return ResponseEntity.ok(updProduct);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer-Authentication")
    public ResponseEntity<Product> delete(@PathVariable Long id)
    {
        Product deleteProduct = productService.findById(id); 
        productService.deleteProdct(id);
        return ResponseEntity.ok(deleteProduct);
    }

    @GetMapping("/search")
    public ResponseEntity<LinkedList<Product>> searchProduct(@RequestParam("query") String query){
        try {
            LinkedList<Product> search = productService.search(query);
            return ResponseEntity.ok(search);
        }catch (Exception e){
            throw new CustomErrorException (
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }
    @GetMapping("/best-selling")
    public ResponseEntity<Product> getBestSellingProduct() {
        Product bestSellingProduct = productService.getBestSellingProduct();
        if (bestSellingProduct != null) {
            return ResponseEntity.ok(bestSellingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
