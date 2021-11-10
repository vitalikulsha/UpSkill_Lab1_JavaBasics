package io.github.vitalikulsha.WebBasicsREST.model;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Category {
    private Long id;
    private String title;
    private List<Author> authors;

    public static Category toModel(CategoryEntity entity) {
        Category model = new Category();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setAuthors(entity.getAuthors().stream().map(Author::toModel).collect(Collectors.toList()));
        return model;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
