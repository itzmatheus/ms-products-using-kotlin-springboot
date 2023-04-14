package com.kotlin.springboot.kotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
import java.util.UUID

@Entity
data class Category(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var name: String = "",

)