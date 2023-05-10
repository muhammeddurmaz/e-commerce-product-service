package com.durmaz.product.service;

import com.durmaz.product.service.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO saveCategory(CategoryDTO productDTO);
    CategoryDTO updateCategory(CategoryDTO productDTO);
    void deleteCategory(Long id);
}
