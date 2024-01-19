package com.sample.mydemo.person.controller

import com.sample.mydemo.person.dto.PersonDto
import com.sample.mydemo.person.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/test")
class PersonController (val personService: PersonService) {

    //get all Persons
    @GetMapping("")
    fun getAllPersons(): ResponseEntity<List<PersonDto>> = ResponseEntity.ok(personService.getAllPersons())

    // create Person
    @PostMapping("")
    fun createPerson(@RequestBody personDto: PersonDto): ResponseEntity<PersonDto> {
        val savedPerson = personService.savePerson(personDto)
        return ResponseEntity(savedPerson, HttpStatus.CREATED)
    }

    //get by personId
    @GetMapping("/{id}")
    fun getPersonById(@PathVariable("id") id: Int): ResponseEntity<PersonDto> {

        return ResponseEntity(personService.findById(id), HttpStatus.FOUND)
    }

    //delete by personId
    @DeleteMapping("/{id}")
    fun deletePersonById(@PathVariable id: Int): ResponseEntity<String> {
        personService.deleteById(id)

        return ResponseEntity(String.format("Person with id %s deleted successfully", id), HttpStatus.OK)
    }

    //Update by personId
    @PutMapping("/{id}")
    fun updatePersonById(@PathVariable id: Int, @RequestBody personDto: PersonDto) : ResponseEntity<PersonDto> {

        /*val updatedPerson = existingPerson.get()
            .copy(name = personDto.firstName, email = personDto.email)*/
        val person = personService.updatePerson(id, personDto)
        return ResponseEntity(person, HttpStatus.OK)
    }
}
