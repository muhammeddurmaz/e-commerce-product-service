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
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
