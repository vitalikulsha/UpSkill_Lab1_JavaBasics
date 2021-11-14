package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.controller.CategoryController;
import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoriesIsEmptyException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Author;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final static Logger LOG = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(CategoryEntity category) throws CategoryAlreadyExistsException {
        if (categoryRepository.findByTitle(category.getTitle()) != null) {
            LOG.error("A category with the same name already exists.");
            throw new CategoryAlreadyExistsException("A category with the same name already exists.");
        }
        return Category.toModel(categoryRepository.save(category));
    }

    public Category getCategory(Long id) throws CategoryNotFoundException {
        CategoryEntity category = categoryRepository.findById(id).get();
        if (category == null) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        return Category.toModel(category);
    }

    public List<Category> getAllCategories() throws CategoriesIsEmptyException {
        Iterable<CategoryEntity> categoryList = categoryRepository.findAll();
        if (categoryList == null) {
            LOG.error("The List of categories is empty");
            throw new CategoriesIsEmptyException("The List of categories is empty");
        }
        List<Category> categories = new ArrayList<>();
        categoryList.forEach(c -> categories.add(Category.toModel(c)));
        return categories;
    }

    public Category updateCategory(Long id, CategoryEntity category) throws CategoryNotFoundException {
        if (categoryRepository.findById(id).get() == null) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        category.setId(id);
        return Category.toModel(categoryRepository.save(category));
    }

    public Long deleteCategory(Long id) throws CategoryNotFoundException {
//        CategoryEntity category = categoryRepository.findById(id).get();
        if (categoryRepository.findById(id).get() == null) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
        return id;
    }
}
