package com.github.lashu.foodideaprovider.homeFood.recipe

data class CreateRecipeRequest(
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: Category,
    val performers: List<String>?
)

data class UpdateRecipeRequest(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: Category,
    val performers: List<String>?
)

data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: Category,
    val performers: List<String>?
)

data class Ingredient(
    val name: String,
    val amount: Double,
    val unit: String
)

enum class Category {
    BREAKFAST, DINNER, SNACK
}
