package com.durmaz.product.service.mapper;

import com.durmaz.product.domain.Category;
import com.durmaz.product.domain.Product;
import com.durmaz.product.service.CategoryService;
import com.durmaz.product.service.dto.CategoryDTO;
import com.durmaz.product.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper{

    private final CategoryService categoryService;

    public ProductMapperImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(productDTO.getCategoryId());
        Category category = CategoryDTO.toEntity(categoryDTO);
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                category,
                productDTO.getAvailableStock()
        );
    }

    @Override
    public ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getAvailableStock()
        );
    }
}
