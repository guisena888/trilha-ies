package com.fatec.ies.trilha.controller

import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.model.SkinDto
import com.fatec.ies.trilha.repository.CategoryRepository
import com.fatec.ies.trilha.repository.SkinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/skin")
class SkinController {

    @Autowired
    lateinit var skinRepository: SkinRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @GetMapping
    fun findAll(@RequestParam(required = false) category: String?): MutableIterable<Skin> {
        return if(!category.isNullOrBlank()) {
            skinRepository.findByCategory(categoryRepository.findByNameContainingIgnoreCase(category).get())
        } else 
            skinRepository.findAll()
    }
       

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Skin>{
        var skinOptional = skinRepository.findById(id)

        if(!skinOptional.isPresent) return ResponseEntity.notFound().build()

        return ResponseEntity.ok(skinOptional.get())
    }

    @PostMapping
    fun create(@RequestBody skinDto: SkinDto): Skin {
        return skinRepository.save(skinDto.convert(categoryRepository))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<Any>{
        var skinOptional = skinRepository.findById(id)

        if(!skinOptional.isPresent) return ResponseEntity.notFound().build()

        var skin = skinOptional.get()

        skinRepository.delete(skin)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable("id") id: Long,
                   @RequestBody skinDto: SkinDto): ResponseEntity<Skin> {

        var skinOptional = skinRepository.findById(id)
        if(!skinOptional.isPresent) return ResponseEntity.notFound().build()

        var categoryOptionl = categoryRepository.findById(skinDto.categoryId)
        if(!categoryOptionl.isPresent) return ResponseEntity.notFound().build()

        var skin = skinOptional.get()

        skin.name = skinDto.name
        skin.imageUrl= skinDto.imageUrl
        skin.price = skinDto.price
        skin.category = categoryOptionl.get()
        skinRepository.save(skin)
        return ResponseEntity.ok(skin)
    }

}
