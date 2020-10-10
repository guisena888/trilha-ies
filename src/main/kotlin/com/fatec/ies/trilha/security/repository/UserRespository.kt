package com.fatec.ies.trilha.security.repository

import com.fatec.ies.trilha.security.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRespository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}