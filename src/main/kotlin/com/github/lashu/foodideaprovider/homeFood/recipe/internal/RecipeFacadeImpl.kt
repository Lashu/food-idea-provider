package com.github.lashu.foodideaprovider.homeFood.recipe.internal

import com.github.lashu.foodideaprovider.homeFood.recipe.CreateRecipeRequest
import com.github.lashu.foodideaprovider.homeFood.recipe.Recipe
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeFacade
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeNotFoundException
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeRepository
import java.util.UUID.randomUUID

class RecipeFacadeImpl(private val recipeRepository: RecipeRepository): RecipeFacade {

    override fun createRecipe(createRecipeRequest: CreateRecipeRequest): Recipe {
        val recipe = Recipe(
            id = randomUUID().toString(),
            name = createRecipeRequest.name,
            ingredients = createRecipeRequest.ingredients,
            steps = createRecipeRequest.steps,
            sweet = createRecipeRequest.sweet,
            category = createRecipeRequest.category,
            performers = createRecipeRequest.performers
        )

        return recipeRepository.save(recipe)
    }

    override fun getRecipe(id: String): Recipe {
        return recipeRepository.findById(id) ?: throw RecipeNotFoundException(id)
    }

    override fun getRecipes(): List<Recipe> {
        return recipeRepository.findAll()
    }

}