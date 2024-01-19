package com.sample.mydemo.person.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.util.Date

@Getter
@Setter
@Entity
@Table(name = "person")
data class Person (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var phone: Number = 0,
    var createdDate: Date = Date(),
    var email: String = "",
    val password: String = ""
)
