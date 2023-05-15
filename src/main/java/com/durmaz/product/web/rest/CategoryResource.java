package com.durmaz.product.web.rest;

import com.durmaz.product.service.CategoryService;
import com.durmaz.product.service.dto.CategoryDTO;
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
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getId() != null){
            throw new BadRequestAlertException("A new category cannot already have an ID");
        }
        CategoryDTO result = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/categorys/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "id")Long id, @RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!Objects.equals(id, categoryDTO.getId())) {
            throw new BadRequestAlertException("incompatible id");
        }
        CategoryDTO result = categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/categorys")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> result = categoryService.getAllCategory();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/categorys/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "id") Long id){
        CategoryDTO result = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/categorys/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id")Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
