package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody AuthorEntity author, @RequestParam Long categoryId) {
        try {
            authorService.addAuthor(author, categoryId);
            return ResponseEntity.ok("Category added successfully!");
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAuthor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorService.updateAuthor(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
