package com.durmaz.product.service.mapper;

import com.durmaz.product.domain.Product;
import com.durmaz.product.service.dto.ProductDTO;

public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDto(Product product);
}
