package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.AuthorNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.service.AuthorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final static Logger LOG = Logger.getLogger(AuthorController.class);

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorService.getAuthorById(id));
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAuthors() {
        try {
            return ResponseEntity.ok(authorService.getAllAuthors());
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorEntity author) {
        try {
            long updId = authorService.updateAuthor(id, author);
            return ResponseEntity.ok("Author #" + updId + " updated successfully!");
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            long delId = authorService.deleteAuthor(id);
            return ResponseEntity.ok("Author #" + delId + " deleted successfully!");
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
