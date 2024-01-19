package com.sample.mydemo.person.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(var resourceName: String, var fieldName: String, var fieldValue: Number) : RuntimeException() {

}
