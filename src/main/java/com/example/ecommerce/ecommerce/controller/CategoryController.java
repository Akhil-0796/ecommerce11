package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.model.Category;
import com.example.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("/get{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") String categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if(!category.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(category.get(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category response = categoryService.update(category);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") String categoryId){
        boolean response = categoryService.deleteCategory(categoryId);
        if(response) return "Deleted.";
        else return "No Category Found";
    }
}
