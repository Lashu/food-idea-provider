package com.github.lashu.foodideaprovider.homeFood.recipe

interface RecipeRepository {
    fun save(recipe: Recipe): Recipe
    fun findById(id: String): Recipe?
    fun findAll(): List<Recipe>
}