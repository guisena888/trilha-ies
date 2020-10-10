package com.fatec.ies.trilha.repository

import com.fatec.ies.trilha.model.Category
import com.fatec.ies.trilha.model.Skin
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SkinRepository : CrudRepository<Skin, Long> {
    fun findByCategory(category: Category): MutableIterable<Skin>
}

interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByNameContainingIgnoreCase(name: String): Optional<Category>
    fun findByName(name: String): MutableIterable<Category>
}