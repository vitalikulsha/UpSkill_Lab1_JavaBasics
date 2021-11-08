package io.github.vitalikulsha.WebBasicsREST.model;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;

public class Category {
    private Long id;
    private String title;

    public static Category toModel(CategoryEntity entity){
        Category model = new Category();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
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
}
