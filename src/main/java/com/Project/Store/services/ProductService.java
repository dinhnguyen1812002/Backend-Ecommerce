package com.Project.Store.services;

import com.Project.Store.controller.UploadController;
import com.Project.Store.entity.Product;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.CategoryRepository;
import com.Project.Store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public void deleteProdct(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product findById(Long id){
       return productRepository.findById(id).orElseThrow(()-> new NotFoundException("not found:"+ id));
    }

}
