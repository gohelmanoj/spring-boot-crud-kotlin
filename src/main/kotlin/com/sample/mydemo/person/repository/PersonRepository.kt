package com.sample.mydemo.person.repository

import com.sample.mydemo.person.entity.Person
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface PersonRepository : CrudRepository<Person, Int> {

    fun findByEmail(email: String):Optional<Person>
}
