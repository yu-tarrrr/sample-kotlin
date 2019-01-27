package com.example.sample_kotlin.infrastructure

import com.example.sample_kotlin.controller.resources.LgtmEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository: JpaRepository<LgtmEntity,String>