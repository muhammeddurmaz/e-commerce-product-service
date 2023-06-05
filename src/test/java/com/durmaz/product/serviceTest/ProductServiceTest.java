package com.durmaz.product.serviceTest;

import com.durmaz.product.domain.Category;
import com.durmaz.product.domain.Product;
import com.durmaz.product.repository.ProductRepository;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.impl.ProductServiceImpl;
import com.durmaz.product.service.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        // Test verileri
        Long id = 1L;
        String name = "Test Ürünü";
        String description = "Bu bir test ürünüdür.";
        Double price = 10.0;
        Category category = new Category("Test Kategori","Test");
        int availableStock = 5;

        ProductDTO productDTO = new ProductDTO(id, name, description, price, category.getId(), availableStock);

        Product product = new Product(id, name, description, price, category, availableStock);

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Metodu çağır
        ProductDTO result = productService.saveProduct(productDTO);

        // Sonucu doğrula
        assertEquals(1L, result.getId());  // Kaydedilen ürünün ID'si 1 olmalı
        assertEquals("Test Ürünü", result.getName());  // Kaydedilen ürünün adı "Test Ürünü" olmalı
        assertEquals(10.0, result.getPrice());  // Kaydedilen ürünün fiyatı 10.0 olmalı

        // Repository'nin save metodunun çağrıldığını kontrol ediyo
        verify(productRepository).save(Mockito.any(Product.class));
    }


    @Test
    public void testUpdateProduct() {
        // Test verileri

        Long id = 1L;
        String updatedProductName = "Test Ürünü - Güncelleme";
        String updatedDescription = "Bu bir test ürünüdür. Güncellendi";
        Double updatedProductPrice = 20.0;
        Category updatedCategory = new Category("Test Kategori","Test");
        int updatedCvailableStock = 5;

        String name = "Test Ürünü";
        String description = "Bu bir test ürünüdür.";
        Double price = 10.0;
        Category category = new Category("Test Kategori","Test");
        int availableStock = 5;

        ProductDTO existingProductDTO = new ProductDTO(id, name, description, price, category.getId(), availableStock);

        Product existingProduct = new Product(id, name, description, price, category, availableStock);

        Product updatedProduct = new Product(id,updatedProductName,updatedDescription,updatedProductPrice,updatedCategory,updatedCvailableStock);


        when(productRepository.existsById(id)).thenReturn(true);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // Metodu çağır
        ProductDTO existingProductU = new ProductDTO(id, updatedProductName, description, updatedProductPrice, category.getId(), availableStock);
        ProductDTO result = productService.updateProduct(existingProductU);

        // Sonucu doğrula
        assertEquals(id, result.getId());
        assertEquals(updatedProductName, result.getName());
        assertEquals(updatedProductPrice, result.getPrice());

        // Repository'nin save metodunun çağrıldığını ve doğru parametrelerle çağrıldığını kontrol et
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        // Test verileri
        Long productId = 1L;
        String productName = "Test Product";
        String productDescription = "Test Description";
        Double productPrice = 10.0;
        Category productCategory = new Category(1L, "Test Category", "TEST");
        Integer availableStock = 20;

        Product product = new Product(productId, productName, productDescription, productPrice, productCategory, availableStock);
        ProductDTO productDTO = new ProductDTO(productId, productName, productDescription, productPrice, productCategory.getId(), availableStock);

        // Mock işlemleri
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDTO);

        // Metodu çağır ve sonucu kontrol et
        ProductDTO result = productService.getProductById(productId);

        Assertions.assertEquals(productId, result.getId());
        Assertions.assertEquals(productName, result.getName());
        Assertions.assertEquals(productDescription, result.getDescription());
        Assertions.assertEquals(productPrice, result.getPrice());
        Assertions.assertEquals(productCategory.getId(), result.getCategoryId());
        Assertions.assertEquals(availableStock, result.getAvailableStock());

        // productRepository.findById() çağrıldığında beklenen metotların çağrıldığını kontrol et
        verify(productRepository, times(1)).findById(productId);
        verifyNoMoreInteractions(productRepository);

        // productMapper.toDto() çağrıldığını kontrol et
        verify(productMapper, times(1)).toDto(product);
        verifyNoMoreInteractions(productMapper);
    }


}
