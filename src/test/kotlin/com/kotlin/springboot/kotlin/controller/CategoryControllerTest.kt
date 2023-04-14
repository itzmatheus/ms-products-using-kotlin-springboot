package com.kotlin.springboot.kotlin.controller

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate

    @Autowired
    lateinit var categoryRepository: CategoryRepository
    @BeforeEach
    fun clearDb() {
        categoryRepository.deleteAll()
    }

    @Test
    fun shouldAddCategory() {
        val category = Category(name = "ELETRONIC")

        val categoryReturned = template.postForObject("/category", category, Category::class.java)

        Assertions.assertNotNull(categoryReturned)
        Assertions.assertNotNull(categoryReturned.id)
        Assertions.assertEquals(category.name, categoryReturned.name)

    }

    @Test
    fun shouldUpdateCategory() {
        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)

        category.name = "TOY"
        template.put("/category", category)

        val categoryReturned = template.getForObject("/category/{id}", Category::class.java, category.id)

        Assertions.assertNotNull(categoryReturned)
        Assertions.assertNotNull(categoryReturned.id)
        Assertions.assertEquals("TOY", categoryReturned.name)

    }

    @Test
    fun shouldListAllCategory() {
        categoryRepository.save(Category(name = "ELETRONIC"))

        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val entity = HttpEntity(null, headers)

        val response = template.exchange<List<Category>>("/category", HttpMethod.GET, entity)

        Assertions.assertTrue(response.statusCode.is2xxSuccessful)
        Assertions.assertEquals(1, response.body?.size)
        Assertions.assertEquals("ELETRONIC", response.body?.get(0)?.name)

    }

    @Test
    fun shouldDeleteACategory() {

        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)

        Assertions.assertEquals(1, categoryRepository.count())
        template.delete("/category/{id}", category.id)
        Assertions.assertEquals(0, categoryRepository.count())

    }
}