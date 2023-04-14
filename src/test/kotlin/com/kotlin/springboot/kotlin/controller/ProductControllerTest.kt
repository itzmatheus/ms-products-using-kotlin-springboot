package com.kotlin.springboot.kotlin.controller

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.entity.Product
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    @Order(1)
    fun shouldAddProducts() {
        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)

        val product = Product(name = "iPhone 11", price = 2500, category = category)

        val productReturned = template.postForObject("/products", product, Product::class.java)

        Assertions.assertNotNull(productReturned)
        Assertions.assertNotNull(productReturned.id)
        Assertions.assertEquals(product.name, productReturned.name)
        Assertions.assertEquals(product.category?.name, productReturned.category?.name)

    }

}