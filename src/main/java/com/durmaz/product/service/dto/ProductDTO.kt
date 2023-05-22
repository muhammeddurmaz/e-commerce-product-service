package com.durmaz.product.service.dto

import com.durmaz.product.domain.Category
import com.durmaz.product.domain.Product

data class ProductDTO(
        val id: Long?,
        val name: String,
        val description: String,
        val price: Double,
        val categoryId: Long?,
        val availableStock: Int,
){
}
