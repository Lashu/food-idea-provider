package com.github.lashu.foodideaprovider.homeFood.api.internal

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class ErrorResponseFactory {

    fun create(request: HttpServletRequest, exception: Exception): ErrorHolder {
        return ErrorHolder(
            Error(
                exception.message,
                exception.javaClass.simpleName,
                request.requestURI
            )
        )
    }

}

data class ErrorHolder(val error: Error)

data class Error(
    val message: String?,
    val code: String,
    val path: String
)
