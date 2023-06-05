package com.durmaz.product.web.rest;

import com.durmaz.product.service.ProductService;
import com.durmaz.product.service.dto.ProductDTO;
import com.durmaz.product.service.dto.ResponseDTO;
import com.durmaz.product.service.exception.BadRequestAlertException;
import com.durmaz.product.service.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Validated
public class ProductResource {
    private final static String ENTITY_NAME = "procut";
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody ProductDTO productDTO){
        if (productDTO.getId() != null){
            throw new BadRequestAlertException("A new product cannot already have an ID");
        }
        ProductDTO result = productService.saveProduct(productDTO);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Create Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseDTO> uptadeProduct(@PathVariable(value = "id") Long id,@RequestBody ProductDTO productDTO){
        if (productDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!Objects.equals(id, productDTO.getId())) {
            throw new BadRequestAlertException("incompatible id");
        }

        ProductDTO result = productService.updateProduct(productDTO);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Updated Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> getAllProduct(){
        List<ProductDTO> result = productService.getAllProducts();
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get All Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/products/ids")
    public ResponseEntity<ResponseDTO> getAllProductByIds(@RequestBody @NotNull List<Long> ids){
        List<ProductDTO> result = productService.getAllProductByIds(ids);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get All Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable(value = "id") @NotNull Long id){
        ProductDTO result = productService.getProductById(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/product-price/{id}")
    public ResponseEntity<ResponseDTO> getProductPrice(@PathVariable(value = "id") @NotNull Long id){
        Double result = productService.getProductPriceById(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/product-sumPrice")
    public ResponseEntity<ResponseDTO> getSumProductPrice(@RequestParam List<Long> ids){
        Double result = productService.getSumProductPriceByIds(ids);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable(value = "id")Long id){
        productService.deleteProduct(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Deleted Success",ENTITY_NAME)
                .success(true)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

}
