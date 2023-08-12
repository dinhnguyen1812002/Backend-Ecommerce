package com.Project.Store.controller;

import com.Project.Store.entity.Category;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.CategoryRepository;
import com.Project.Store.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    CategoryServices categoryServices;
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping
    public List<Category> getAllBook()
    {
        return categoryServices.getAllCategory();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category cate = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy mã sách: "+id));
        return ResponseEntity.ok(cate);
    }
    @PostMapping("/add")
    public Category createCategory(@RequestBody Category category){

        return categoryServices.saveCategory(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category)
    {
        Optional<Category> categoryUpdate = categoryRepository.findById(id);
        return  categoryUpdate.map(category1->{
            category.setId(category.getId());
             return new ResponseEntity<>(categoryRepository.save(category),HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id)
    {
        Category deleteCategory = categoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Không tìm thấy mã sách: "+id));
        categoryServices.deleteCategory(id);
        return ResponseEntity.ok(deleteCategory);
    }
}
