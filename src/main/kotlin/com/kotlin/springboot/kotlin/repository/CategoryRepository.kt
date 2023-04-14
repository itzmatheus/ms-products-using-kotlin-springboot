package com.kotlin.springboot.kotlin.repository

import com.kotlin.springboot.kotlin.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, String>