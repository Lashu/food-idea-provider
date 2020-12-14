package com.github.lashu.foodideaprovider.homeFood.api.internal

import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ErrorHandler(private val errorResponseFactory: ErrorResponseFactory) {

    companion object {
        private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(request: HttpServletRequest, exception: Exception): ErrorHolder {
        logger.error(exception.message, exception)
        return errorResponseFactory.create(request, exception)
    }

    @ExceptionHandler(RecipeNotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handleRecipeNotFoundException(request: HttpServletRequest, exception: Exception): ErrorHolder {
        logger.warn(exception.message, exception)
        return errorResponseFactory.create(request, exception)
    }

}

