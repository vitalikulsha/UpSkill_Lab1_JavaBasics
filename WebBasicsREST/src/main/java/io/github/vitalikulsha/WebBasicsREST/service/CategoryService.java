package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoriesIsEmptyException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Author;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(CategoryEntity category) throws CategoryAlreadyExistsException {
        if (categoryRepository.findByTitle(category.getTitle()) != null) {
            throw new CategoryAlreadyExistsException("A category with the same name already exists.");
        }
        return Category.toModel(categoryRepository.save(category));
    }

    public Category read(Long id) throws CategoryNotFoundException {
        CategoryEntity category = categoryRepository.findById(id).get();
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        return Category.toModel(category);
    }

    public List<Category> readAll() throws CategoriesIsEmptyException {
        Iterable<CategoryEntity> categoryList = categoryRepository.findAll();
        if (categoryList == null) {
            throw new CategoriesIsEmptyException("The List of categories is empty");
        }
        List<Category> categories = new ArrayList<>();
        categoryList.forEach(c -> categories.add(Category.toModel(c)));
        return categories;
    }

    //TODO
    public Category update(Long id, CategoryEntity category) {
        CategoryEntity categoryDB = categoryRepository.findById(id).get();
        if (category.equals(categoryDB)){
            return Category.toModel(categoryRepository.save(category));
        }
        return Category.toModel(categoryRepository.save(category));
    }

    public Long delete(Long id) throws CategoryNotFoundException {
        CategoryEntity category = categoryRepository.findById(id).get();
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
        return id;
    }
}
