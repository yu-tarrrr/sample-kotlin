package com.example.sample_kotlin.controller

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.controller.resources.LgtmTopResponse
import com.example.sample_kotlin.infrastructure.LgtmRepository
import com.example.sample_kotlin.service.DeleteImageService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping
class LgtmController(private val lgtmRepository: LgtmRepository) {

    /**
     * 取得用メソッド
     */
    @GetMapping("/name")
    fun geLgtmImages() : MutableList<LgtmTopResponse> {
        // 現在時刻の取得をしておく
        var now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, -7)
        val baseDate = now.time

        // 一旦Repositoryから全部取得しておく
        val responseList = DeleteImageService(lgtmRepository).getImages()

        // 上の処理に対してストリームしていく
        var builtList : MutableList<LgtmTopResponse> = mutableListOf()
        responseList.forEach{
            it -> builtList.add(LgtmTopResponse().buildWithEntity(it, baseDate))
        }

        // 整形済みのリストをNewフラグの是非でバラす
        var topResponseList : MutableList<LgtmTopResponse> = mutableListOf()
        var nonNewList : MutableList<LgtmTopResponse> = mutableListOf()
        builtList.forEach{ it -> if (it.isNew) {
            topResponseList.add(it)
        } else {
            nonNewList.add(it)
        }
        }

        // それぞれシャッフルして合体させる
        topResponseList.shuffle()
        topResponseList.addAll(nonNewList.shuffled())

        // レスポンスを返却する
        return topResponseList
    }
git
    /**
     * 削除用メソッド
     */
    @GetMapping("/delete")
    fun deleteLgtmImage(model : Model, @RequestParam(name = "q") deleteImageUrl : String) {
        // 受け取ったUrlをEntityに詰める
        var lgtmEntity = LgtmEntity()
        lgtmEntity.apply { imageUrl = deleteImageUrl }

        // 実行する
        try {
            DeleteImageService(lgtmRepository).deleteImage(lgtmEntity)
        } catch (ex : Exception) {
            print(ex.cause)
        }

    }
}