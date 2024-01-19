package com.sample.mydemo.person.service

import com.sample.mydemo.person.dto.PersonDto
import com.sample.mydemo.person.entity.Person


interface PersonService {

    fun getAllPersons(): List<PersonDto>

    fun savePerson(personDto: PersonDto): PersonDto

    fun findById(personId: Int): PersonDto

    fun deleteById(personId: Int)

    fun updatePerson(personId: Int, personDto: PersonDto): PersonDto

    fun dtoToEntity(personDto: PersonDto) : Person

    fun entityToDto(person: Person) : PersonDto

}
