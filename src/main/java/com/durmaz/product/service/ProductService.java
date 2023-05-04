package com.durmaz.product.service;

import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    void saveProduct(ProductDTO productDTO);
    void updateProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
}
