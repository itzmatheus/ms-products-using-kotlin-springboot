package com.kotlin.springboot.kotlin.steps.req001

import com.kotlin.springboot.kotlin.entity.Category
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals

class CreateCategoryGWT {

    private val sharedSteps = SharedSteps()
    private lateinit var result: Any

    @When("i create the category")
    fun iCreateTheCategory(dataTable: DataTable) {
        val data = dataTable.asMap()
        val categoryName = data["categoryName"]
        print("Creating category: $categoryName")
        try {
            result = sharedSteps.saveOrUpdateCategory(categoryName)
            println("Category created")
        } catch (e: RuntimeException) {
            result = e
        }
    }

    @Then("the category should be created with success")
    fun theCategoryShouldBeCreatedWithSuccess() {
        println("Category created with success")
        assertEquals("1", (result as Category).id, "Category id should be 1")
    }

    @Then("the category should not be created")
    fun theCategoryShouldNotBeCreated(dataTable: DataTable) {
        val data = dataTable.asMap()
        val categoryName = data["categoryName"]
        println("Category not created")
        assertEquals("Invalid category name: $categoryName", (result as RuntimeException).message, "Invalid category name: $categoryName")
    }

}