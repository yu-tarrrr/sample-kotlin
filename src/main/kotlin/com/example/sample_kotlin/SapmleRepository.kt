package com.example.sample_kotlin

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository: JpaRepository<SampleEntity,String>