package com.kotlin.springboot.kotlin.controller

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryController(
    val categoryService: CategoryService,
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Category? = categoryService.findById(id)

    @GetMapping
    fun findAll(): List<Category> = categoryService.findAll()

    @PostMapping
    fun add(@RequestBody category: Category): Category = categoryService.saveOrUpdate(category)

    @PutMapping
    fun update(@RequestBody category: Category): Category = categoryService.saveOrUpdate(category)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: String): Unit = categoryService.removeById(id)

}