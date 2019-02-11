package com.example.sample_kotlin.controller.resources

import java.util.*

/**
 * フロントに返す用のDTOクラス
 */
class LgtmTopResponse {
    var imageUrl = String()
    var imageName = String()
    var imageLgtmUrl = String()
    var updateDatetime = Date()
    var isNew = false

    fun buildWithEntity(lgtmEntity: LgtmEntity,baseDate :Date) : LgtmTopResponse{
        return LgtmTopResponse().apply {
            imageUrl = lgtmEntity.imageUrl
            imageName = lgtmEntity.imageName
            imageLgtmUrl = lgtmEntity.imageLgtmUrl
            updateDatetime = lgtmEntity.updateDatetime
            isNew = lgtmEntity.updateDatetime.after(baseDate)
        }
    }
}