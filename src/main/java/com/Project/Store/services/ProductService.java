package com.Project.Store.services;

import com.Project.Store.entity.Product;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.ICategoryRepository;
import com.Project.Store.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository ICategoryRepository;
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


}
