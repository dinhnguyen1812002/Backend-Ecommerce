package com.Project.Store.services;

import com.Project.Store.entity.Category;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {
    @Autowired
    ICategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public boolean isCategoryExit(Category category){
        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        return existingCategory.isPresent();
    }
    public Category getCategoryById( Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Not found "+ id));
    }
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
