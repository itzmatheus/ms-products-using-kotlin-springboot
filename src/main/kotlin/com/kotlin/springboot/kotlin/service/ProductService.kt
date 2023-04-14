package com.kotlin.springboot.kotlin.service

import com.kotlin.springboot.kotlin.entity.Product
import com.kotlin.springboot.kotlin.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRepository: ProductRepository,
) {

    fun findById(id: String): Product? = productRepository.findById(id).get()

    fun findAll(): List<Product> = productRepository.findAll()

    fun saveOrUpdate(product: Product): Product {
        return productRepository.save(product)
    }

    fun removeById(id: String): Unit = productRepository.deleteById(id)

}