package com.fatec.ies.trilha.repository

import com.fatec.ies.trilha.model.Category
import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.model.UserSkin
import com.fatec.ies.trilha.security.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SkinRepository : CrudRepository<Skin, Long> {
    fun findByCategory(category: Category): MutableIterable<Skin>
}

interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByNameContainingIgnoreCase(name: String): Optional<Category>
    fun findByName(name: String): MutableIterable<Category>
}

interface UserSkinRepository : CrudRepository<UserSkin, Long> {
    fun findByUser(user: User): MutableIterable<UserSkin>
    fun findByUserAndSkin(user: User, skin: Skin): Optional<UserSkin>
//    fun findBySkinInAAndUserAndIsEnabled(skins: MutableIterable<Skin>, user): MutableIterable<UserSkin>
}
