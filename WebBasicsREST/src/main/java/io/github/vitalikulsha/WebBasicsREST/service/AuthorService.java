package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.model.Author;
import io.github.vitalikulsha.WebBasicsREST.repository.AuthorRepository;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Author createAuthor(AuthorEntity author, Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).get();
        author.setCategory(category);
        return Author.toModel(authorRepository.save(author));
    }

    public Author updateAuthor(Long id) {
        AuthorEntity author = authorRepository.findById(id).get();
        return Author.toModel(authorRepository.save(author));
    }
}
