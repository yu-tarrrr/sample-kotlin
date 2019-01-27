package com.example.sample_kotlin.controller

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.controller.resources.LgtmTopResponse
import com.example.sample_kotlin.infrastructure.LgtmRepository
import com.example.sample_kotlin.service.LgtmRepositoryAccessor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/")
class LgtmTopController(private val lgtmRepository: LgtmRepository) {

    /**
     * 取得用メソッド
     */
    @GetMapping
    fun getLgtmImages(model: Model) : String {
        // 現在時刻の取得をしておく
        val now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, -7)
        val baseDate = now.time

        // 一旦Repositoryから全部取得しておく
        val responseList = LgtmRepositoryAccessor(lgtmRepository).getImages()

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

        model.addAttribute("imageResources", topResponseList)

        return "top"
    }

    /**
     * 削除用メソッド
     */
    @GetMapping("/delete")
    fun deleteLgtmImage(model : Model, @RequestParam(name = "q") deleteImageUrl : String) : String{
        // 受け取ったUrlをEntityに詰める
        var lgtmEntity = LgtmEntity()
        lgtmEntity.apply { imageUrl = deleteImageUrl }

        // 実行する
        try {
            LgtmRepositoryAccessor(lgtmRepository).deleteImage(lgtmEntity)
        } catch (ex : Exception) {
            model.addAttribute("error", ex.message)
        }
        return "redirect:/"
    }
}