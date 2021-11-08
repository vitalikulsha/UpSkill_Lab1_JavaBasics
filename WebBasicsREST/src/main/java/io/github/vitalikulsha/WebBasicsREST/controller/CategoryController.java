package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity registration(@RequestBody CategoryEntity category) {
        try {
            if (categoryRepository.findByTitle(category.getTitle()) != null) {
                return ResponseEntity.badRequest().body("A category with the same name already exists.");
            }
            categoryRepository.save(category);
            return ResponseEntity.ok("Category added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping
    public ResponseEntity getCategories() {
        try {
            return ResponseEntity.ok("The server is running!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
