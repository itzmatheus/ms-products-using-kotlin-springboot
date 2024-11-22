package com.kotlin.springboot.kotlin.steps.req001

import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given

class SharedGTW {

    @Given("i have a category with name")
    fun iHaveACategoryWith(dataTable: DataTable) {
        val data = dataTable.asMap()
        val categoryName = data["categoryName"]
        println("my category name is $categoryName")
    }

}