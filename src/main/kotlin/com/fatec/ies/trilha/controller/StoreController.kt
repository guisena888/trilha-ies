package com.fatec.ies.trilha.controller

import com.fatec.ies.trilha.model.CatalogSkinDto
import com.fatec.ies.trilha.model.MySkinDto
import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.model.UserSkin
import com.fatec.ies.trilha.repository.CategoryRepository
import com.fatec.ies.trilha.repository.SkinRepository
import com.fatec.ies.trilha.repository.UserSkinRepository
import com.fatec.ies.trilha.security.models.UserUtils
import com.fatec.ies.trilha.security.repository.UserRespository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/store")
class StoreController {

    @Autowired
    lateinit var userRepository: UserRespository

    @Autowired
    lateinit var skinRepository: SkinRepository

    @Autowired
    lateinit var userSkinRepository: UserSkinRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @PostMapping("/{id}")
    fun buySkin(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val user = UserUtils.findLoggedUser(userRepository)
        val skin = skinRepository.findById(id).get()
        val userSkin = UserSkin(user = user, skin = skin)
        userSkinRepository.save(userSkin)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getSkins(): MutableIterable<MySkinDto> {
        val user = UserUtils.findLoggedUser(userRepository)
        val userSkins =  userSkinRepository.findByUser(user)
        val skinsList: MutableList<MySkinDto> = mutableListOf()
        userSkins.forEach {
            skinsList.add(MySkinDto(
                    id = it.skin?.id!!,
                    name = it.skin?.name!!,
                    imageUrl = it.skin?.imageUrl!!,
                    category = it.skin?.category!!,
                    price = it.skin?.price!!,
                    isEnabled = it.isEnabled!!
            ))
        }

        return skinsList
    }

    @PostMapping("/equip/{id}")
    fun equipSkin(@PathVariable("id") id: Long): ResponseEntity<Any> {

        val user = UserUtils.findLoggedUser(userRepository)
        val skin = skinRepository.findById(id).get()

        userSkinRepository.findByUser(user)
                .filter { it.skin?.category?.id!! == skin.category.id && it.isEnabled!!}
                .forEach{
                    it.isEnabled = false
                    userSkinRepository.save(it)
                }

        val userSkin = userSkinRepository.findByUserAndSkin(user, skin).get()
        userSkin.isEnabled = true
        userSkinRepository.save(userSkin)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/catalog")
    fun getCatalog(@RequestParam(required = false) category: String?): MutableIterable<CatalogSkinDto> {
        val user = UserUtils.findLoggedUser(userRepository)
        val skins: MutableIterable<Skin>
        if (!category.isNullOrBlank()) {
            skins = skinRepository.findByCategory(categoryRepository.findByNameContainingIgnoreCase(category).get())
        } else
            skins = skinRepository.findAll()

        val skinsList: MutableList<CatalogSkinDto> = mutableListOf()
        skins.forEach{
            skinsList.add(CatalogSkinDto(
                    id = it.id!!,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    category = it.category,
                    price = it.price,
                    bought = userSkinRepository.existsByUserAndSkin(user, it)
            ))
        }

        return skinsList
    }
}