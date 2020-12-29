package com.github.lashu.foodideaprovider.homeFood.api.internal

import com.github.lashu.foodideaprovider.homeFood.recipe.InvalidCategoryException
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ErrorHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(request: HttpServletRequest, exception: Exception): ErrorHolder {
        logger.error(exception.message, exception)
        return errorHolder(exception.message, exception.javaClass.simpleName, request.requestURI)
    }

    @ExceptionHandler(RecipeNotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handleRecipeNotFoundException(request: HttpServletRequest, exception: Exception): ErrorHolder {
        logger.warn(exception.message, exception)
        return errorHolder(exception.message, exception.javaClass.simpleName, request.requestURI)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleMethodArgumentNotValidException(request: HttpServletRequest, exception: MethodArgumentNotValidException): ErrorHolder {
        logger.warn(exception.message, exception)
        return errorHolder(exception.bindingResult.fieldErrors.joinToString { it.field + " " + it.defaultMessage }, exception.javaClass.simpleName, request.requestURI)
    }

    @ExceptionHandler(InvalidCategoryException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleInvalidCategoryException(request: HttpServletRequest, exception: Exception): ErrorHolder {
        logger.warn(exception.message, exception)
        return errorHolder(exception.message, exception.javaClass.simpleName, request.requestURI)
    }

    fun errorHolder(message: String?, code: String, path: String): ErrorHolder {
        return ErrorHolder(Error(message, code, path))
    }

}
