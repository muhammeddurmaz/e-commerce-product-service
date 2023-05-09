package com.durmaz.product.domain

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category  @JvmOverloads constructor (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long? = 0,

        @Column(name = "name" , nullable = false)
        val name: String,

        @Column(name = "code")
        val code: String,
        ){}
