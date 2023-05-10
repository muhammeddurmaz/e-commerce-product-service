package com.durmaz.product.service.dto

import com.durmaz.product.domain.Category


data class CategoryDTO(
        val id: Long?,
        val name: String,
        val code: String
){
    companion object{
        @JvmStatic
        fun toDto(category: Category) : CategoryDTO{
            return CategoryDTO(
                    category.id,
                    category.name,
                    category.code
            )
        }
        @JvmStatic
        fun toEntity(categoryDTO: CategoryDTO) : Category{
            return Category(
                    categoryDTO.id,
                    categoryDTO.name,
                    categoryDTO.code
            )
        }
    }
}

