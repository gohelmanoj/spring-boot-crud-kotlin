package com.sample.mydemo.person.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
data class PersonDto (

    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: Number = 0
)
