package com.durmaz.product.web.rest;

import com.durmaz.product.service.ProductService;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        if (productDTO.getId() != null){
            throw new BadRequestAlertException("A new product cannot already have an ID");
        }
        ProductDTO result = productService.saveProduct(productDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> uptadeProduct(@PathVariable(value = "id") Long id,@RequestBody ProductDTO productDTO){
        if (productDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!Objects.equals(id, productDTO.getId())) {
            throw new BadRequestAlertException("incompatible id");
        }

        ProductDTO result = productService.updateProduct(productDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> result = productService.getAllProducts();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(value = "id") Long id){
        ProductDTO result = productService.getProductById(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id")Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
