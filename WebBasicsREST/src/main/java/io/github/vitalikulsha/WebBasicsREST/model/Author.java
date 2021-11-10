package io.github.vitalikulsha.WebBasicsREST.model;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;

public class Author {
    private Long id;
    private String firstName;
    private String lastName;

    public static Author toModel(AuthorEntity entity){
        Author model = new Author();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
