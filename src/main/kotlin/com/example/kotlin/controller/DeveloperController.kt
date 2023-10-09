package com.example.kotlin.controller

import com.example.kotlin.entity.Developer
import com.example.kotlin.service.DeveloperService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/developer")
class DeveloperController(val developerService: DeveloperService) {

    @GetMapping("/hello")
    fun helloWorld() = "Hello World!"

    @PostMapping("/add")
    fun addData(@RequestBody developer: Developer): Mono<Boolean> {
        return developerService.addDeveloper(developer)
    }

    @GetMapping("/get")
    fun getData(@RequestBody employeeId: String): Mono<Developer> {
        return developerService.getDeveloper(employeeId);
    }

    @PostMapping("/delete/{id}")
    fun deleteData(@PathVariable("id") employeeId: String): Mono<Boolean> {
        return developerService.removeDeveloper(employeeId)
    }
}