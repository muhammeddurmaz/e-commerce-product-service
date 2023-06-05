package com.durmaz.product.service;

import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    void deleteProduct(Long id);

    List<ProductDTO> getAllProductByIds(List<Long> ids);

    Double getProductPriceById(Long id);

    Double getSumProductPriceByIds(List<Long> id);
}
