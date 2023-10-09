package com.example.kotlin.service

import com.example.kotlin.entity.Developer
import com.example.kotlin.repository.DeveloperRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class DeveloperServiceImpl(val developerRepository: DeveloperRepository): DeveloperService {

    override fun addDeveloper(developer: Developer): Mono<Boolean> {
        return developerRepository.saveData(developer)
    }

    override fun getDeveloper(employeeId: String): Mono<Developer> {
        return developerRepository.getData(employeeId)
    }

    override fun removeDeveloper(employeeId: String): Mono<Boolean> {
        return developerRepository.removeData(employeeId)
    }

}