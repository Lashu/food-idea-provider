package com.github.lashu.foodideaprovider.homeFood.api

import com.github.lashu.foodideaprovider.homeFood.api.internal.ElementsNotBlank
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class RecipeRequestDto(
    @get:NotBlank(message = "can not be blank")
    val name: String,

    @get:Valid
    @get:Size(min = 1, message = "can not be empty")
    val ingredients: List<IngredientDto>?,

    @get:ElementsNotBlank
    @get:Size(min = 1, message = "can not be empty")
    val steps: List<String>?,

    val sweet: Boolean,
    val category: String,

    @get:ElementsNotBlank
    val performers: Set<String>?
)

data class RecipesResponseDto(
    val recipes: List<RecipeResponseDto>
)

data class RecipeResponseDto(
    val id: String,
    val name: String,
    val ingredients: List<IngredientDto>?,
    val steps: List<String>?,
    val sweet: Boolean,
    val category: String,
    val performers: Set<String>?
)

data class IngredientDto(
    @get:NotBlank(message = "can not be blank")
    val name: String,

    @get:Positive(message = "must be positive")
    val amount: Double,

    @get:NotBlank(message = "can not be blank")
    val unit: String
)
