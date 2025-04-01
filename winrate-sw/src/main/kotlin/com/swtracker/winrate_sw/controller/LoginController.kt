package com.swtracker.winrate_sw.controller

import com.swtracker.winrate_sw.service.UserService
//import com.swtracker.login.security.JwtUtil
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/login")
class LoginController (
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    // private val jwUtil: JwtUtil
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        userService.registerUser(request.username, request.email, request.password)
        return ResponseEntity.ok("Usuario registrado com sucesso")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<String> {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        val token = jwtUtil.generateToken(request.email)
        return ResponseEntity.ok(token)
    }
}

data class RegisterRequest(val username: String, val email: String, val password: String)
data class LoginRequest(val email: String, val password: String)

