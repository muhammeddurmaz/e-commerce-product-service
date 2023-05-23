package com.durmaz.product.serviceTest;

import com.durmaz.product.domain.Category;
import com.durmaz.product.domain.Product;
import com.durmaz.product.repository.ProductRepository;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

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

        ProductDTO productDTO = new ProductDTO(id, name, description, price, category, availableStock);

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

        ProductDTO existingProductDTO = new ProductDTO(id, name, description, price, category, availableStock);

        Product existingProduct = new Product(id, name, description, price, category, availableStock);

        Product updatedProduct = new Product(id,updatedProductName,updatedDescription,updatedProductPrice,updatedCategory,updatedCvailableStock);


        when(productRepository.existsById(id)).thenReturn(true);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // Metodu çağır
        ProductDTO existingProductU = new ProductDTO(id, updatedProductName, description, updatedProductPrice, category, availableStock);
        ProductDTO result = productService.updateProduct(existingProductU);

        // Sonucu doğrula
        assertEquals(id, result.getId());
        assertEquals(updatedProductName, result.getName());
        assertEquals(updatedProductPrice, result.getPrice());

        // Repository'nin save metodunun çağrıldığını ve doğru parametrelerle çağrıldığını kontrol et
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
