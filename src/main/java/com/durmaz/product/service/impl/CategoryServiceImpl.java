package com.durmaz.product.service.impl;

import com.durmaz.product.domain.Category;
import com.durmaz.product.repository.CategoryRepository;
import com.durmaz.product.service.CategoryService;
import com.durmaz.product.service.dto.CategoryDTO;
import com.durmaz.product.service.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryDTO::toDto)
                .orElseThrow(() -> new CategoryNotFoundException("Category could not found by id " + id));
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO result = categoryDTO.toDto(savedCategory);
        return result;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO productDTO) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
