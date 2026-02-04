package com.examen.inventoryconfig.util

import com.examen.inventoryconfig.exception.UnauthorizedActionException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

object AuthUtils {

    fun getUserId(claim: String = "sub"): String {
        val auth = SecurityContextHolder.getContext().authentication
        val jwt = (auth as? JwtAuthenticationToken)?.token
            ?: throw UnauthorizedActionException("No hay JWT en el contexto de seguridad")

        val value = jwt.getClaimAsString(claim)
        return value ?: throw UnauthorizedActionException("Token sin claim '$claim'")
    }
}
