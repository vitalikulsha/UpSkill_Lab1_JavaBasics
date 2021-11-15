package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.AuthorNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Author;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.repository.AuthorRepository;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final static Logger LOG = Logger.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void addAuthor(AuthorEntity author, Long categoryId) throws CategoryNotFoundException {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            LOG.error("Category not found. id = " + categoryId);
            throw new CategoryNotFoundException("Category not found");
        }
        author.setCategory(category.get());
        authorRepository.save(author);
        LOG.info("Author added successfully! fulName = " + author.getFirstName() + " " + author.getLastName());
    }

    public Author getAuthorById(Long id) throws AuthorNotFoundException {
        Optional<AuthorEntity> author = getAuthorFromDB(id);
        LOG.info("Author received successfully! id = " + id);
        return Author.toModel(author.get());
    }

    public List<Author> getAllAuthors() throws AuthorNotFoundException {
        Iterable<AuthorEntity> authorList = authorRepository.findAll();
        if (!authorList.iterator().hasNext()) {
            LOG.error("No authors found: list of authors id empty.");
            throw new AuthorNotFoundException("No authors found.");
        }
        List<Author> authors = new ArrayList<>();
        authorList.forEach(a -> authors.add(Author.toModel(a)));
        LOG.info("All authors received successfully!");
        return authors;
    }

    public Long updateAuthor(Long id, AuthorEntity author) throws AuthorNotFoundException {
        Optional<AuthorEntity> authorDB = getAuthorFromDB(id);
        if (author.getCategory() == null) {
            author.setCategory(authorDB.get().getCategory());
        }
        author.setId(id);
        authorRepository.save(author);
        LOG.info("Author updated successfully! id = " + id);
        return id;
    }


    public Long deleteAuthor(Long id) throws AuthorNotFoundException {
        Optional<AuthorEntity> authorDB = getAuthorFromDB(id);
        authorRepository.deleteById(id);
        LOG.info("Author deleted successfully! id = " + id);
        return id;
    }

    private Optional<AuthorEntity> getAuthorFromDB(Long id) throws AuthorNotFoundException {
        Optional<AuthorEntity> authorDB = authorRepository.findById(id);
        if (authorDB.isEmpty()) {
            LOG.error("Author not found. id = " + id);
            throw new AuthorNotFoundException("Author not found");
        }
        return authorDB;
    }

}
