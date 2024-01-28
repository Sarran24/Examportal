package com.examportal.controller;

import com.examportal.model.exam.Category;
import com.examportal.service.exam.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception {
        Category categoryResponse = categoryService.create(category);
        logger.info("category with {} has been saved",category.getTitle());
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        logger.info("retrieved all categories");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{cid}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long cid) {
        Category category = categoryService.getById(cid);
        logger.info("retrieved category with id {}", cid);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/update/{cid}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long cid, @RequestBody Category category) {
        category.setCid(cid);
        Category updatedCategory = categoryService.update(category);
        logger.info("updated category with id {}", cid);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
}
