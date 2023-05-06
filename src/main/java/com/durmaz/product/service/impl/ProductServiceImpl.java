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
                .orElseThrow(() -> new ProductNotFoundException("Product could not found by id " + id));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = ProductDTO.toEntity(productDTO);
        productRepository.save(product);
        ProductDTO result = ProductDTO.toDto(product);
        return result;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (!productRepository.existsById(productDTO.getId())) {
            throw new ProductNotFoundException("Product not found for update with id " + productDTO.getId());
        }
        Product product = ProductDTO.toEntity(productDTO);
        productRepository.save(product);
        ProductDTO result = ProductDTO.toDto(product);
        return result;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for delete with id " + id);
        }
        productRepository.deleteById(id);
    }
}
