package com.fatec.ies.trilha.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
class Skin(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var name: String,

        var imageUrl: String,

        @ManyToOne
        var category: Category,

        var price: BigDecimal

)

@Entity
class Category (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var name: String,
        var imageWidth: Long,
        var imageHeight: Long
)