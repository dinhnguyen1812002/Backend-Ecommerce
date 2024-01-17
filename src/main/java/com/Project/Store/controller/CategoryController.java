package com.Project.Store.controller;

import com.Project.Store.entity.Category;
import com.Project.Store.payload.response.ApiResponse;
import com.Project.Store.payload.response.MessageResponse;
import com.Project.Store.repository.ICategoryRepository;
import com.Project.Store.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ICategoryRepository ICategoryRepository;
    @GetMapping
    public List<Category> getAllBook()
    {
        return categoryServices.getAllCategory();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category cate = categoryServices.getCategoryById(id);
        return ResponseEntity.ok(cate);
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createCategory(@RequestBody Category category){
        if(categoryServices.isCategoryExit(category)){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("category is exit"));
        }
        Category saveCategory =  categoryServices.saveCategory(category);

        return ResponseEntity.ok(new MessageResponse("Ok"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category)
    {
        Optional<Category> categoryUpdate = ICategoryRepository.findById(id);
        return  categoryUpdate.map(category1->{
            category.setId(category.getId());
             return new ResponseEntity<>(ICategoryRepository.save(category),HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id)
    {
        Category deleteCategory = categoryServices.getCategoryById(id);
        categoryServices.deleteCategory(id);
        return ResponseEntity.ok(deleteCategory);
    }
}
