package com.durmaz.product.service.impl;

import com.durmaz.product.domain.Product;
import com.durmaz.product.repository.ProductRepository;
import com.durmaz.product.service.ProductService;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product could not found by id" + id));
    }

    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product product = ProductDTO.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = ProductDTO.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
