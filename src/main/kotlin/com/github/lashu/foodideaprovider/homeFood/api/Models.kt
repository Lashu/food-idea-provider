package com.github.lashu.foodideaprovider.homeFood.api

data class CreateRecipeRequestDto(
    val name: String,
    val ingredients: List<IngredientDto>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: String,
    val performers: List<String>?
)

data class RecipesResponseDto(
    val recipes: List<RecipeResponseDto>
)

data class RecipeResponseDto(
    val id: String,
    val name: String,
    val ingredients: List<IngredientDto>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: String,
    val performers: List<String>?
)

data class IngredientDto(
    val name: String,
    val amount: Double,
    val unit: String
)
