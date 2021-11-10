package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity add(CategoryEntity category) throws CategoryAlreadyExistsException {
        if (categoryRepository.findByTitle(category.getTitle()) != null) {
            throw new CategoryAlreadyExistsException("A category with the same name already exists.");
        }
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) throws CategoryNotFoundException {
        CategoryEntity category = categoryRepository.findById(id).get();
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        return Category.toModel(category);
    }

    public Long delete(Long id){
        categoryRepository.deleteById(id);
        return id;
    }
}
