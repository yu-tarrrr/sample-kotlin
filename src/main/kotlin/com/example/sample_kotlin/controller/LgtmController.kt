package com.example.sample_kotlin.controller

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.controller.resources.LgtmTopResponse
import com.example.sample_kotlin.infrastructure.SampleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping
class LgtmController(val sampleRepository: SampleRepository) {

    @GetMapping("/name")
    fun geLgtmImages() : MutableList<LgtmTopResponse> {
        // 現在時刻の取得をしておく
        var now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, -7)
        val baseDate = now.time

        // 一旦Repositoryから全部取得しておく
        val responseList : List<LgtmEntity> = sampleRepository.findAll()

        // 上の処理に対してストリームしていく
        var builtList : MutableList<LgtmTopResponse> = mutableListOf()
        responseList.forEach{
            it -> builtList.add(LgtmTopResponse().buildWithEntity(it, baseDate))
        }

        // 整形済みのリストをNewフラグの是非でバラす
        var topReponseList : MutableList<LgtmTopResponse> = mutableListOf()
        var nonNewList : MutableList<LgtmTopResponse> = mutableListOf()
        builtList.forEach{ it -> if (it.isNew) {
            topReponseList.add(it)
        } else {
            nonNewList.add(it)
        }
        }

        // それぞれシャッフルして合体させる
        topReponseList.shuffle()
        topReponseList.addAll(nonNewList.shuffled())

        // レスポンスを返却する
        return topReponseList
    }
}