package com.github.lashu.foodideaprovider.homeFood.recipe

interface RecipeFacade {
    fun createRecipe(createRecipeRequest: CreateRecipeRequest): Recipe
    fun getRecipe(id: String): Recipe
    fun getRecipes(): List<Recipe>
}