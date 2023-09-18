package com.example.kotlin.config

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
@RequiredArgsConstructor
class ApplicationConfig {

    @Autowired
    private val environment: Environment? = null

    @Qualifier(value = "activeProfile")
    @Bean
    fun getActiveProfile(): String? {

        return environment?.activeProfiles?.get(0)
    }
}