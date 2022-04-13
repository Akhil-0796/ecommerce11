package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
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

    /**
     * add new category
     * @param category
     * @return
     */
    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    /**
     * return category based on id
     * @param categoryId
     * @return
     */
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") String categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if(!category.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(category.get(),HttpStatus.OK);
    }

    /**
     * update category
     * @param category
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category response = categoryService.update(category);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * remove category based on id
     * @param categoryId
     * @return
     */
    @DeleteMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") String categoryId){
        boolean response = categoryService.deleteCategory(categoryId);
        if(response) return "Deleted.";
        else return "No Category Found";
    }
}
