package com.fatec.ies.trilha.controller

import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.model.UserSkin
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

    @PostMapping("/{id}")
    fun buySkin(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val user = UserUtils.findLoggedUser(userRepository)
        val skin = skinRepository.findById(id).get()
        val userSkin = UserSkin(user = user, skin = skin)
        userSkinRepository.save(userSkin)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getSkins(): MutableIterable<UserSkin> {
        val user = UserUtils.findLoggedUser(userRepository)
        return userSkinRepository.findByUser(user)
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
}