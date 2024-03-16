package com.Project.Store.services;

import com.Project.Store.entity.Product;
import com.Project.Store.entity.SalesTransaction;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.ICategoryRepository;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.SalesTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository ICategoryRepository;
    @Autowired
    private SalesTransactionRepository salesTransactionRepository;
    public void deleteProdct(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product findById(Long id){
       return productRepository.findById(id).orElseThrow(()-> new NotFoundException("not found:"+ id));
    }
    public Iterator<Product> findAll(){
        return productRepository.findAll().iterator();
    }
    public Product save(Product product){
       return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }
    public LinkedList<Product> search (String query){

        return productRepository.searcProducts(query);
    }

    public Product getBestSellingProduct() {
        List<SalesTransaction> transactions = salesTransactionRepository.findAll();

        // Calculate total quantity sold for each product
        Map<Product, Long> productQuantities = transactions.stream()
                .collect(Collectors.groupingBy(SalesTransaction::getProducts,
                        Collectors.summingLong(SalesTransaction::getQuantity)));

        // Find the product with the highest total quantity sold
        return productQuantities.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
