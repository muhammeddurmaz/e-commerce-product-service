package com.durmaz.product.domain

import javax.persistence.*


@Entity
@Table(name = "products")
data class Product @JvmOverloads constructor(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val description: String,

        @Column(nullable = false)
        val price: Double,

        @Column(nullable = false)
        val category: String,

        @Column(name = "available_stock", nullable = false)
        val availableStock: Int,
)