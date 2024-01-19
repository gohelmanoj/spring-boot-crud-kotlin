package com.sample.mydemo.person.service.impl

import com.sample.mydemo.person.dto.PersonDto
import com.sample.mydemo.person.entity.Person
import com.sample.mydemo.person.exceptions.EmailAlreadyExistException
import com.sample.mydemo.person.exceptions.ResourceNotFoundException
import com.sample.mydemo.person.repository.PersonRepository
import com.sample.mydemo.person.service.PersonService
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import java.util.*


@Service
@AllArgsConstructor
class PersonServiceImpl (private val personRepository: PersonRepository) : PersonService  {

    override fun getAllPersons(): List<PersonDto> {
        val persons = personRepository.findAll()
        return persons.map { person -> entityToDto(person) }
    }


    override fun savePerson(personDto: PersonDto): PersonDto {

        val personOptional = personRepository.findByEmail(personDto.email)

        if (personOptional.isPresent) {
            throw EmailAlreadyExistException("Email Already Exist")
        }

        var person = dtoToEntity(personDto)
        person.createdDate = Date()
        person = personRepository.save(person)

        return entityToDto(person)
    }


    override fun findById(personId: Int): PersonDto {

        val  personOptional = personRepository.findById(personId)
        if (personOptional.isEmpty) {
            throw ResourceNotFoundException("Person", "id", personId)
        }
        return entityToDto(personOptional.get())
    }

    override fun deleteById(personId: Int) {
        val person = personRepository.findById(personId)

        return if (person.isPresent) personRepository.deleteById(personId)
        else throw ResourceNotFoundException("Person", "id", personId)
    }

    override fun updatePerson(personId: Int, personDto: PersonDto): PersonDto {

        val  personOptional = personRepository.findById(personId)
        if (personOptional.isEmpty) {
            throw ResourceNotFoundException("Person", "id", personId)
        }

        var person = personOptional.get()
        person.firstName = personDto.firstName
        person.lastName = personDto.lastName
        person.email = personDto.email
        person.phone = personDto.phone

        person = personRepository.save(person)

        return entityToDto(person)
    }

    override fun dtoToEntity(personDto: PersonDto): Person {

        return Person(
            id = personDto.id,
            firstName = personDto.firstName,
            lastName = personDto.lastName,
            email = personDto.email,
            phone = personDto.phone,
            password = "",
            createdDate = Date()
        )
    }

    override fun entityToDto(person: Person): PersonDto {

        return PersonDto(
            id = person.id,
            firstName = person.firstName,
            lastName = person.lastName,
            email = person.email,
            phone = person.phone
        )
    }
}
