package com.Project.Store.controller;

import com.Project.Store.entity.Category;
import com.Project.Store.entity.Product;
import com.Project.Store.exception.CustomErrorException;

import com.Project.Store.exception.NotFoundException;
import com.Project.Store.services.CategoryServices;
import com.Project.Store.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
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


    @PostMapping("/create")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        try {
            Category cat = categoryServices.getCategoryById(product.getCategory().getId());
            product.setCategory(cat);
            Product newProduct = productService.save(product);
//            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
            return ResponseEntity.ok(newProduct);
        }catch (NullPointerException e){
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
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
        oldProduct.setCategory(product.getCategory());
        Product updProduct = productService.save(oldProduct);
        return ResponseEntity.ok(updProduct);
    }

    @DeleteMapping("/{id}")
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
}
