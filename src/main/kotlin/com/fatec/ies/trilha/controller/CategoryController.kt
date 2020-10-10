package com.fatec.ies.trilha.controller

import com.fatec.ies.trilha.model.Category
import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.repository.CategoryRepository
import com.fatec.ies.trilha.repository.SkinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryController {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @GetMapping
    fun findAll(@RequestParam(required = false) name: String?): MutableIterable<Category> {
        return if(!name.isNullOrBlank()) {
            categoryRepository.findByName(name)
        } else
            categoryRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Category>{
        var categoryOptional = categoryRepository.findById(id)

        if(!categoryOptional.isPresent) return ResponseEntity.notFound().build()

        return ResponseEntity.ok(categoryOptional.get())
    }

    @PostMapping
    fun create(@RequestBody category: Category): Category {
        return categoryRepository.save(category)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<Any>{
        var categoryOptional = categoryRepository.findById(id)

        if(!categoryOptional.isPresent) return ResponseEntity.notFound().build()

        var category = categoryOptional.get()

        categoryRepository.delete(category)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable("id") id: Long,
                   @RequestBody categoryForm: Category): ResponseEntity<Category> {
        var categoryOptional = categoryRepository.findById(id)

        if(!categoryOptional.isPresent) return ResponseEntity.notFound().build()

        var category = categoryOptional.get()

        category.name = categoryForm.name
        category.imageHeight = categoryForm.imageHeight
        category.imageWidth = categoryForm.imageWidth
        categoryRepository.save(category)
        return ResponseEntity.ok(category)
    }

}