package com.example.kotlin.service

import com.example.kotlin.entity.Developer
import reactor.core.publisher.Mono

interface DeveloperService {
    fun addDeveloper(developer: Developer): Mono<Boolean>

    fun getDeveloper(employeeId: String): Mono<Developer>

    fun removeDeveloper(employeeId: String): Mono<Boolean>
}