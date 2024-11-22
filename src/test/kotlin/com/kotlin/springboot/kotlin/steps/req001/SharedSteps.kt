package com.kotlin.springboot.kotlin.steps.req001

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import com.kotlin.springboot.kotlin.service.CategoryService
import io.mockk.every
import io.mockk.mockk

class SharedSteps {

    private val categoryRepository: CategoryRepository = mockk()
    private val categoryService = CategoryService(
        categoryRepository = categoryRepository
    )

    private fun getCategoryService(): CategoryService {
        return categoryService
    }

    fun saveOrUpdateCategory(categoryName: String?): Category {
        every { categoryRepository.save(any<Category>()) } returns Category(
            id = "1",
            name = categoryName
        )
        val category = Category(
            name = categoryName
        )
        return getCategoryService().saveOrUpdate(category)
    }

    fun saveOrUpdateCategory(category: Category): Category {
        every { categoryRepository.save(any<Category>()) } returns category
        return getCategoryService().saveOrUpdate(category)
    }

}