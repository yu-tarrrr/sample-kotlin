package com.example.sample_kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sample")
class SampleEntity {
    @Id
    var id : Int = 0
    @Column(name = "family_name")
    var familyName = String()
    @Column(name = "last_name")
    var lastName = String()
    var age : Int = 0
    var sex = String()
}