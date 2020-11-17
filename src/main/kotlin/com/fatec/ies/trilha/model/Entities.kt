package com.fatec.ies.trilha.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fatec.ies.trilha.security.models.User
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

@Entity
class UserSkin(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @JsonIgnore
        @ManyToOne
        var user: User? = null,

        @ManyToOne
        var skin: Skin? = null,

        var isEnabled: Boolean? = false
)