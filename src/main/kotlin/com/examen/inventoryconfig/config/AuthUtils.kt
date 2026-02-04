package com.examen.inventoryconfig.config

import com.examen.inventoryconfig.exception.UnauthorizedActionException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt

object AuthUtils {
    fun currentUserId(): String {
        val auth = SecurityContextHolder.getContext().authentication
            ?: throw UnauthorizedActionException("Missing authentication")

        val jwt = auth.principal as? Jwt
            ?: throw UnauthorizedActionException("Missing JWT principal")

        return jwt.getClaimAsString("sub")
            ?: throw UnauthorizedActionException("Missing 'sub' claim in token")
    }
}
