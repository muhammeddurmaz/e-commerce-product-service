package com.durmaz.product.service.dto

import java.util.Objects

data class ResponseDTO<T> @JvmOverloads constructor(
        var message: HashMap<String,String> ? = null,
        var success: Boolean? = false,
        var data : T ? = null
){
    fun message(message: String,entityName: String): ResponseDTO<T> {
        val map = HashMap<String,String>()
        map.put("message",message)
        map.put("entity",entityName)
        this.message = map
        return this
    }

    fun success(success: Boolean): ResponseDTO<T> {
        this.success = success
        return this
    }

    fun data(data: T): ResponseDTO<T> {
        this.data = data
        return this
    }

    fun build(): ResponseDTO<T> {
        return ResponseDTO(message, success, data)
    }
}
