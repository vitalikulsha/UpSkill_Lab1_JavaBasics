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
    public ResponseEntity registration(@RequestBody CategoryEntity category) {
        try {
            categoryService.add(category);
            return ResponseEntity.ok("Category added successfully!");
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping
    public ResponseEntity getOneCategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
