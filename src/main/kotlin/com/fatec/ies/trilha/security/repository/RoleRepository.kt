package com.fatec.ies.trilha.security.repository

import com.fatec.ies.trilha.security.models.ERole
import com.fatec.ies.trilha.security.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: ERole): Optional<Role>
}