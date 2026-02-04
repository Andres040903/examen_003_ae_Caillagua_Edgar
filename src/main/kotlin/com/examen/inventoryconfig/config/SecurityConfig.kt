package com.examen.inventoryconfig.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/public/**").permitAll()

                it.requestMatchers(HttpMethod.GET, "/api/rules/**").authenticated()

                it.requestMatchers(HttpMethod.POST, "/api/rules/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.PUT, "/api/rules/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.PATCH, "/api/rules/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.DELETE, "/api/rules/**").hasRole("ADMIN")

                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth ->
                oauth.jwt { jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                }
            }
            .build()
    }

    private fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val converter = JwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            val groups = jwt.getClaimAsStringList("cognito:groups") ?: emptyList()
            groups.map { SimpleGrantedAuthority("ROLE_$it") }
        }
        return converter
    }
}
