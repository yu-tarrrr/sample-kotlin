package com.example.sample_kotlin.service

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.infrastructure.LgtmRepository

class LgtmRepositoryAccesor(private val lgtmRepository: LgtmRepository ) {

    /**
     * 取得用のメソッド
     */
    fun getImages() :List<LgtmEntity>{ return lgtmRepository.findAll()}

    /**
     * 削除用のメソッド
     */
    fun deleteImage(lgtmEntity: LgtmEntity) { lgtmRepository.delete(lgtmEntity) }
}