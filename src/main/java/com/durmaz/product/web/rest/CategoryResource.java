package com.durmaz.product.web.rest;

import com.durmaz.product.service.CategoryService;
import com.durmaz.product.service.dto.CategoryDTO;
import com.durmaz.product.service.dto.ResponseDTO;
import com.durmaz.product.service.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CategoryResource {
    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categorys")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getId() != null){
            throw new BadRequestAlertException("A new category cannot already have an ID");
        }
        CategoryDTO result = categoryService.saveCategory(categoryDTO);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Create Success")
                .success(true)
                .data(result)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/categorys/{id}")
    public ResponseEntity<ResponseDTO> updateCategory(@PathVariable(name = "id")Long id, @RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!Objects.equals(id, categoryDTO.getId())) {
            throw new BadRequestAlertException("incompatible id");
        }
        CategoryDTO result = categoryService.updateCategory(categoryDTO);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Update Success")
                .success(true)
                .data(result)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/categorys")
    public ResponseEntity<ResponseDTO> getAllCategory(){
        List<CategoryDTO> result = categoryService.getAllCategory();
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get All Success")
                .success(true)
                .data(result)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/categorys/{id}")
    public ResponseEntity<ResponseDTO> getCategoryById(@PathVariable(name = "id") Long id){
        CategoryDTO result = categoryService.getCategoryById(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success")
                .success(true)
                .data(result)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/categorys/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable(name = "id")Long id){
        categoryService.deleteCategory(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Delete Success")
                .success(true)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}
