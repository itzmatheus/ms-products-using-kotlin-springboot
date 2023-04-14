package com.kotlin.springboot.kotlin.controller

import com.kotlin.springboot.kotlin.entity.Product
import com.kotlin.springboot.kotlin.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    val productService: ProductService
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Product? = productService.findById(id)

    @GetMapping
    fun findAll(): List<Product> = productService.findAll()

    @PostMapping
    fun add(@RequestBody product: Product): Product = productService.saveOrUpdate(product)

    @PutMapping
    fun update(@RequestBody product: Product): Product = productService.saveOrUpdate(product)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: String): Unit = productService.removeById(id)

}