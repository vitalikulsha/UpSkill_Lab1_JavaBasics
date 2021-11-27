package io.github.vitalikulsha.WebBasicsREST.mapper;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(source = "category.title", target = "categoryTitle")
    Author toAuthor(AuthorEntity entity);
}
