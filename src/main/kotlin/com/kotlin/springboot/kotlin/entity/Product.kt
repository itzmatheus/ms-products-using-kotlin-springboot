package com.kotlin.springboot.kotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull
import java.util.UUID

@Entity
data class Product(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = true)
    var price: Int? = null,

    @ManyToOne
    @JoinColumn(name= "fk_categories_id")
    var category: Category? = null
)