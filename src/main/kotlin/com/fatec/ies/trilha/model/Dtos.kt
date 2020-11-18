package com.fatec.ies.trilha.model

import com.fatec.ies.trilha.repository.CategoryRepository
import java.math.BigDecimal

data class SkinDto(

        var name: String,
        var imageUrl: String,
        var categoryId: Long,
        var price: BigDecimal
) {
    fun convert(categoryRepository: CategoryRepository): Skin {
        val category = categoryRepository.findById(this.categoryId)
        if(category.isPresent == null) throw Exception()
        return Skin(
                null,
                this.name,
                this.imageUrl,
                category.get(),
                this.price
        )
    }
}

data class CatalogSkinDto (
        var id: Long,
        var name: String,
        var imageUrl: String,
        var category: Category,
        var price: BigDecimal,
        var bought: Boolean
)