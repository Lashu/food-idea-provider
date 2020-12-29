package com.github.lashu.foodideaprovider.homeFood.api.internal

data class ErrorHolder(val error: Error)

data class Error(
    val message: String?,
    val code: String,
    val path: String
)
