package com.durmaz.product.service.dto

import com.durmaz.product.domain.Category
import com.durmaz.product.domain.Product

data class ProductDTO(
        val id: Long?,
        val name: String,
        val description: String,
        val price: Double,
        val category: Category,
        val availableStock: Int,
){
    companion object{
        @JvmStatic
        fun toDto(product: Product) : ProductDTO{
            return ProductDTO(
                    product.id,
                    product.name,
                    product.description,
                    product.price,
                    product.category,
                    product.availableStock
            )
        }

        @JvmStatic
        fun toEntity(productDTO: ProductDTO): Product{
            return Product(
                    productDTO.id,
                    productDTO.name,
                    productDTO.description,
                    productDTO.price,
                    productDTO.category,
                    productDTO.availableStock
            )
        }
    }
}
