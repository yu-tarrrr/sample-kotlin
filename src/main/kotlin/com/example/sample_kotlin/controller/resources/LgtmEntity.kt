package com.example.sample_kotlin.controller.resources

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * LGTMテーブル用のEntity
 */
@Entity
@Table(name = "lgtm")
class LgtmEntity {
    @Id
    @Column(name = "image_url")
    var imageUrl = String()
    @Column(name = "image_name")
    var imageName = String()
    @Column(name = "image_lgtm_url")
    var imageLgtmUrl = String()
    @Column(name = "update_datetime")
    var updateDatetime = Date()
}