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
    public ResponseEntity<?> addAuthor(@RequestBody AuthorEntity author, @RequestParam Long categoryId) throws CategoryNotFoundException {
        authorService.addAuthor(author, categoryId);
        return ResponseEntity.ok("Author added successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) throws AuthorNotFoundException {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAuthors() throws AuthorNotFoundException {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorEntity author) throws AuthorNotFoundException {
        long updId = authorService.updateAuthor(id, author);
        return ResponseEntity.ok("Author #" + updId + " updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) throws AuthorNotFoundException {
        long delId = authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author #" + delId + " deleted successfully!");
    }
}
