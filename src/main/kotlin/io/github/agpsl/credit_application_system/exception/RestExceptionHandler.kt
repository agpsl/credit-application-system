package io.github.agpsl.credit_application_system.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetail> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach{
            error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName] = messageError
        }
        return ResponseEntity(
            ExceptionDetail(
                title = "Bad request. Consult the docs",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleValidException(ex: DataAccessException): ResponseEntity<ExceptionDetail> {
        return ResponseEntity(
            ExceptionDetail(
                title = "Bad request. Consult the docs",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                details = mutableMapOf("Data Access Exception" to "Failed do execute query")
            ), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(BusinessException::class)
    fun handleValidException(ex: BusinessException): ResponseEntity<ExceptionDetail> {
        return ResponseEntity(
            ExceptionDetail(
                title = "Bad request. Consult the docs",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = mutableMapOf("Business Exception" to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleValidException(ex: IllegalArgumentException): ResponseEntity<ExceptionDetail> {
        return ResponseEntity(
            ExceptionDetail(
                title = "Bad request. Consult the docs",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = mutableMapOf("Illegal Argument" to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }
}