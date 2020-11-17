package com.fatec.ies.trilha.security.models

import com.fatec.ies.trilha.model.Skin
import com.fatec.ies.trilha.security.repository.UserRespository
import com.fatec.ies.trilha.security.service.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(	name = "users",
        uniqueConstraints = [ UniqueConstraint(columnNames = ["username", "email"])])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank
    @Size(max = 20)
    var username: String? = null

    @NotBlank
    @Size(max = 50)
    @Email
    var email: String? = null

    @NotBlank
    @Size(max = 120)
    var password: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet()

    constructor(username: String, email: String, password: String) {
        this.username = username
        this.email = email
        this.password = password
    }
    constructor(userDetaislImpl: UserDetailsImpl) {
        this.id= userDetaislImpl.id
        this.username = userDetaislImpl.username
        this.email = userDetaislImpl.email
        this.password = userDetaislImpl.password
    }
}

object UserUtils {
    fun findLoggedUser(userRespository: UserRespository): User {
        var userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return userRespository.findByUsername(userDetails.username).get()
    }
}