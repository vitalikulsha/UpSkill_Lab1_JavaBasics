package io.github.vitalikulsha.WebBasicsREST.controller;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
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
    public ResponseEntity createAuthor(@RequestBody AuthorEntity author, @RequestParam Long categoryId) {
        try {
            return ResponseEntity.ok(authorService.createAuthor(author, categoryId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @PutMapping
    public ResponseEntity updateAuthor(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(authorService.updateAuthor(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
