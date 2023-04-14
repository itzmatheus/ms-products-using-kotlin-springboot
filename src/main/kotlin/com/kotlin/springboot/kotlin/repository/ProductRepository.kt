package com.kotlin.springboot.kotlin.repository

import com.kotlin.springboot.kotlin.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, String>