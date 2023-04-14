package com.kotlin.springboot.kotlin.controller

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.entity.Product
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import com.kotlin.springboot.kotlin.repository.ProductRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun clearDb() {
        productRepository.deleteAll()
        categoryRepository.deleteAll()
    }

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

    @Test
    @Order(2)
    fun shouldUpdateProduct() {
        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)
        var product = Product(name = "iPhone 11", price = 2500, category = category)
        product = productRepository.save(product)

        product.name = "iPhone 12"
        template.put("/products", product)

        val productReturned = template.getForObject("/products/{id}", Product::class.java, product.id)

        Assertions.assertNotNull(productReturned)
        Assertions.assertNotNull(productReturned.id)
        Assertions.assertEquals("iPhone 12", productReturned.name)
        Assertions.assertEquals(product.category?.name, productReturned.category?.name)

    }

    @Test
    @Order(3)
    fun shouldListAllProducts() {
        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)
        var product = Product(name = "iPhone 13", price = 2500, category = category)
        product = productRepository.save(product)

        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val entity = HttpEntity(null, headers)

        val response = template.exchange<List<Product>>("/products", HttpMethod.GET, entity)

        Assertions.assertTrue(response.statusCode.is2xxSuccessful)
        Assertions.assertEquals(1, response.body?.size)
        Assertions.assertEquals("iPhone 13", response.body?.get(0)?.name)
        Assertions.assertEquals(product.category?.name, response.body?.get(0)?.category?.name)

    }

    @Test
    @Order(4)
    fun shouldDeleteAProduct() {

        var category = Category(name = "ELETRONIC")
        category = categoryRepository.save(category)
        var product = Product(name = "iPhone 14", price = 2500, category = category)
        product = productRepository.save(product)

        Assertions.assertEquals(1, productRepository.count())
        template.delete("/products/{id}", product.id)
        Assertions.assertEquals(0, productRepository.count())

    }
}