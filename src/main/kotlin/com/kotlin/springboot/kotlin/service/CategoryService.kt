package com.kotlin.springboot.kotlin.service

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
) {

    fun findById(id: String): Category? = categoryRepository.findById(id).get()

    fun findAll(): List<Category> = categoryRepository.findAll()

    fun saveOrUpdate(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun removeById(id: String): Unit = categoryRepository.deleteById(id)

}