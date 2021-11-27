package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity category) throws CategoryAlreadyExistsException {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<?> getCategoryByTitle(@RequestParam String title) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryByTitle(title));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity category) throws CategoryAlreadyExistsException, CategoryNotFoundException {
        long updId = categoryService.updateCategory(id, category);
        return ResponseEntity.ok("Category #" + updId + " updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        long delId = categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category #" + delId + " deleted.");
    }
}
