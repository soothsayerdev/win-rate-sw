package com.swtracker.winrate_sw.service

import com.swtracker.winrate_sw.entity.User
import com.swtracker.winrate_sw.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: BCryptPasswordEncoder) {

    fun registerUser(username: String, email: String, password: String): User {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("Email em uso")
        }

        val hashedPassword = passwordEncoder.encode(password)
        val user = User(username = username, email = email, password = hashedPassword)
        userRepository.save(user)
        return user
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}