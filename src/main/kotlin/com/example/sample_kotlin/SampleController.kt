package com.example.sample_kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SampleController(val sampleRepository: SampleRepository) {

    @GetMapping("/name")
    fun getName() :SampleEntity {
        var responseList : List<SampleEntity> = sampleRepository.findAll()
        return responseList[0]
    }
}