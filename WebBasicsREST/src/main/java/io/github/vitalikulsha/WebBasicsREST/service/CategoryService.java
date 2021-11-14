package io.github.vitalikulsha.WebBasicsREST.service;

import io.github.vitalikulsha.WebBasicsREST.entity.CategoryEntity;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryAlreadyExistsException;
import io.github.vitalikulsha.WebBasicsREST.exception.CategoryNotFoundException;
import io.github.vitalikulsha.WebBasicsREST.model.Category;
import io.github.vitalikulsha.WebBasicsREST.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final static Logger LOG = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(CategoryEntity category) throws CategoryAlreadyExistsException {
        if (categoryRepository.findByTitle(category.getTitle()) != null) {
            LOG.error("A category with the same name already exists.");
            throw new CategoryAlreadyExistsException("A category with the same name already exists.");
        }
        LOG.info("Category added successfully! title = " + category.getTitle());
        categoryRepository.save(category);
    }

    public Category getCategory(Long id) throws CategoryNotFoundException {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        LOG.info("Category received successfully! id = " + id);
        return Category.toModel(category.get());
    }

    public List<Category> getAllCategories() throws CategoryNotFoundException {
        Iterable<CategoryEntity> categoryList = categoryRepository.findAll();
        if (!categoryList.iterator().hasNext()) {
            LOG.error("No categories found.");
            throw new CategoryNotFoundException("No categories found.");
        }
        List<Category> categories = new ArrayList<>();
        categoryList.forEach(c -> categories.add(Category.toModel(c)));
        LOG.info("All categories received successfully!");
        return categories;
    }

    public void updateCategory(Long id, CategoryEntity category) throws CategoryNotFoundException, CategoryAlreadyExistsException {
        if (categoryRepository.findById(id).isEmpty()) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        if (categoryRepository.findByTitle(category.getTitle()) != null) {
            LOG.error("A category with the same name already exists.");
            throw new CategoryAlreadyExistsException("A category with the same name already exists.");
        }
        category.setId(id);
        LOG.info("Category updated successfully! id = " + id + "; newTitle = " + category.getTitle());
    }

    public Long deleteCategory(Long id) throws CategoryNotFoundException {
        if (categoryRepository.findById(id).isEmpty()) {
            LOG.error("Category not found");
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
        LOG.info("Category deleted successfully! id = " + id);
        return id;
    }
}
