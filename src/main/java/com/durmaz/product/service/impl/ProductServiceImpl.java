package com.durmaz.product.service.impl;

import com.durmaz.product.domain.Product;
import com.durmaz.product.repository.ProductRepository;
import com.durmaz.product.service.ProductService;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.exception.ProductNotFoundException;
import com.durmaz.product.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product could not found by id " + id));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        ProductDTO result = productMapper.toDto(savedProduct);
        return result;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (!productRepository.existsById(productDTO.getId())) {
            throw new ProductNotFoundException("Product not found for update process with id " + productDTO.getId());
        }
        Product product = productMapper.toEntity(productDTO);
        Product updatedProduct = productRepository.save(product);
        ProductDTO result = productMapper.toDto(updatedProduct);
        return result;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for delete proccess with id " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAllProductByIds(List<Long> ids){
        return productRepository.findAllById(ids)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Double getProductPriceById(Long id){
        Optional<Double> price = productRepository.getProductPrice(id);
        if(price.isEmpty()){
            throw new ProductNotFoundException("Product Not Found id: "+ id);
        }
        return price.get();
    }

    @Override
    public Double getSumProductPriceByIds(List<Long> ids){
        Optional<Double> sumPrice = productRepository.getSumProductPriceByIds(ids);
        if(sumPrice.isEmpty()){
            throw new ProductNotFoundException("Product Not Found id: ");
        }
        return sumPrice.get();
    }
}
