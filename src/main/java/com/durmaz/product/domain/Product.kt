package com.durmaz.product.domain

import javax.persistence.*


@Entity
@Table(name = "products")
data class Product @JvmOverloads constructor(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long? = 0,

        @Column(name = "name" ,nullable = false)
        val name: String,

        @Column(name = "description")
        val description: String,

        @Column(name = "price" ,nullable = false)
        val price: Double,

        @Column(name = "category" ,nullable = false)
        val category: String,

        @Column(name = "available_stock", nullable = false)
        val availableStock: Int,
){
}