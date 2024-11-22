package com.kotlin.springboot.kotlin.steps.req001

import com.kotlin.springboot.kotlin.entity.Category
import com.kotlin.springboot.kotlin.repository.CategoryRepository
import com.kotlin.springboot.kotlin.service.CategoryService
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals

class UpdateCategoryGWT {

    private val sharedSteps = SharedSteps()

    private lateinit var result: Category

    @When("i update the category")
    fun iUpdateTheCategory(dataTable: DataTable) {
        val data = dataTable.asMap()
        val categoryName = data["categoryName"] as String
        val id = data["id"] as String
        val category = Category(
            name = categoryName,
            id = id
        )
        result = sharedSteps.saveOrUpdateCategory(category)
        println("Category updated")
    }

    @Then("the category should be updated with success")
    fun theCategoryShouldBeUpdatedWithSuccess(dataTable: DataTable) {
        val data = dataTable.asMap()
        val id = data["id"] as String
        println("Category updated with success")
        assertEquals(id, result.id, "Category id should be $id")
    }

}