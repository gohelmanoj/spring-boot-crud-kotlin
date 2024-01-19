package com.sample.mydemo.person.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        resourceNotFoundException: ResourceNotFoundException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetails> {

        val errorDetails =
            ErrorDetails(
                LocalDateTime.now(),
                "${resourceNotFoundException.resourceName} not found with ${resourceNotFoundException.fieldName} : ${resourceNotFoundException.fieldValue}",
                webRequest.getDescription(false),
                "PERSON_NOT_FOUND"
            )

        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EmailAlreadyExistException::class)
    fun handleEmailAlreadyExistException(
        emailAlreadyExistException: EmailAlreadyExistException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetails> {
        logger.error(emailAlreadyExistException.errorMessage)

        val errorDetails =
            ErrorDetails(
                LocalDateTime.now(),
                emailAlreadyExistException.errorMessage,
                webRequest.getDescription(false),
                "TEST_EMAIL_ALREADY_EXIST"
            )

        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(exception: Exception, webRequest: WebRequest): ResponseEntity<ErrorDetails> {

        val errorDetails = exception.message?.let {
            ErrorDetails(
                timeStamp = LocalDateTime.now(),
                message = it,
                path = webRequest.getDescription(false),
                errorCode = "INTERNAL_SERVER_ERROR"
            )
        }

        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val errors : MutableMap<String, String> = mutableMapOf()
        val objectErrors = ex.bindingResult.allErrors

        objectErrors.forEach {
            var fieldName = ""
            val errorMessage = ""
            if (it is FieldError){
                fieldName = it.field
                it.defaultMessage?.let { errorMessage }
            }
            logger.error("Field Name ==> $fieldName <><> ErrorMessage ==> $errorMessage" )
            errors[fieldName] = errorMessage
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}
