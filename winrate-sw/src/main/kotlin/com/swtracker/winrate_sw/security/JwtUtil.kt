package com.swtracker.winrate_sw.security

import io.jsonwebtoken.Jwts
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    private val SECRET_KEY = "secret" // Talvez colocar em variavel de ambiente?

    fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String): Any? {

    }

}