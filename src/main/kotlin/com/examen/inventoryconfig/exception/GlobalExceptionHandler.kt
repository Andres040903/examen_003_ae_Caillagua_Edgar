package com.examen.inventoryconfig.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiError(status = 404, error = "NOT_FOUND", message = ex.message ?: "Not found"))

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiError(status = 400, error = "BAD_REQUEST", message = ex.message ?: "Bad request"))

    @ExceptionHandler(UnauthorizedActionException::class)
    fun handleUnauthorized(ex: UnauthorizedActionException) =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiError(status = 401, error = "UNAUTHORIZED", message = ex.message ?: "Unauthorized"))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val msg = ex.bindingResult.fieldErrors.joinToString("; ") { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiError(status = 400, error = "VALIDATION_ERROR", message = msg))
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiError(status = 500, error = "INTERNAL_ERROR", message = ex.message ?: "Error"))
}
