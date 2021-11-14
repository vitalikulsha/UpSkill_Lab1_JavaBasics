package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final static Logger LOG = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity addCategory(@RequestBody CategoryEntity category) {
        try {
            categoryService.addCategory(category);
            LOG.info("Category added successfully! id = " + category.getId());
            return ResponseEntity.ok("Category added successfully!");
        } catch (CategoryAlreadyExistsException e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategory(@PathVariable Long id) {
        try {
            LOG.info("Category received successfully! id = " + id);
            return ResponseEntity.ok(categoryService.getCategory(id));
        } catch (CategoryNotFoundException e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            LOG.info("All categories received successfully!");
            return ResponseEntity.ok(categoryService.getAllCategories());
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody CategoryEntity category) {
        try {
            LOG.info("Category updated successfully! id = " + id);
            return ResponseEntity.ok(categoryService.updateCategory(id, category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        try {
            LOG.info("Category deleted successfully! id = " + id);
            return ResponseEntity.ok("Category id = " + categoryService.deleteCategory(id) + " deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
