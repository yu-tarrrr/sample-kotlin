package com.example.sample_kotlin.service

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.infrastructure.LgtmRepository
import org.springframework.stereotype.Service

@Service
class LgtmRepositoryAccessor(private val lgtmRepository: LgtmRepository ) {

    /**
     * 取得用のメソッド
     */
    fun getImages() :List<LgtmEntity>{ return lgtmRepository.findAll()}

    /**
     * 登録用のメソッド
     */
    fun postImage(lgtmEntity: LgtmEntity) { lgtmRepository.save(lgtmEntity)}

    /**
     * 削除用のメソッド
     */
    fun deleteImage(lgtmEntity: LgtmEntity) { lgtmRepository.delete(lgtmEntity) }
}