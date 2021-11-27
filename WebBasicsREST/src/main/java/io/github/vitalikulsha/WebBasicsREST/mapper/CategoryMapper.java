package io.github.vitalikulsha.WebBasicsREST.mapper;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface CategoryMapper {
    @Mapping(source = "authors", target = "authorList")
    Category toCategory(CategoryEntity entity);
}
