package io.github.vitalikulsha.WebBasicsREST.repository;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findByTitle(String title);
}
