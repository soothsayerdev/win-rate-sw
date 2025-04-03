package com.swtracker.winrate_sw.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtFilter(private val jwtUtil: JwtUtil) : UsernamePasswordAuthenticationFilter() {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val token = (req as HttpServletRequest).getHeader("Authorization")?.replace("Bearer ", "")

        if (token != null) {
            val email = jwtUtil.validateToken(token)
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(email, null, emptyList())
        }
        chain.doFilter(req, res)
    }
}