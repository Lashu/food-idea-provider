package com.github.lashu.foodideaprovider.homeFood.base

import com.github.lashu.foodideaprovider.homeFood.api.RecipeRequestDto
import com.github.lashu.foodideaprovider.homeFood.api.IngredientDto
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.IngredientDocument
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.RecipeDocument
import com.github.lashu.foodideaprovider.homeFood.recipe.Category
import com.github.lashu.foodideaprovider.homeFood.recipe.CreateRecipeRequest
import com.github.lashu.foodideaprovider.homeFood.recipe.Ingredient
import com.github.lashu.foodideaprovider.homeFood.recipe.UpdateRecipeRequest

import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.SNACK
import static com.github.lashu.foodideaprovider.homeFood.utils.PropertiesVerifier.verifyCustomProperties

trait SampleRecipe {

    UpdateRecipeRequest sampleUpdateRecipeRequest(Map parameters = [:]) {
        Map defaultParameters = [
                id: "recipeId1",
                name: "apple with cinnamon",
                ingredients: [new Ingredient("apple", 2, "big"), new Ingredient("cinnamon", 20, "grams")],
                steps: ["peel apples", "cut apples", "sprinkle cinnamon"],
                sweet: true,
                category: SNACK,
                performers: ["Gentle person"]
        ]
        verifyCustomProperties(parameters, defaultParameters.keySet())

        def arguments = defaultParameters + parameters

        return new UpdateRecipeRequest(
                arguments.id as String,
                arguments.name as String,
                arguments.ingredients as List<Ingredient>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as Category,
                arguments.performers as Set<String>
        )
    }

    CreateRecipeRequest sampleCreateRecipeRequest(Map parameters = [:]) {
        Map defaultParameters = [
                name: "apple with cinnamon",
                ingredients: [new Ingredient("apple", 2, "big"), new Ingredient("cinnamon", 20, "grams")],
                steps: ["peel apples", "cut apples", "sprinkle cinnamon"],
                sweet: true,
                category: SNACK,
                performers: ["Gentle person"]
        ]
        verifyCustomProperties(parameters, defaultParameters.keySet())

        def arguments = defaultParameters + parameters

        return new CreateRecipeRequest(
                arguments.name as String,
                arguments.ingredients as List<Ingredient>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as Category,
                arguments.performers as Set<String>
        )
    }

    RecipeRequestDto sampleRecipeRequest(Map parameters = [:]) {
        Map defaultParameters = [
                name: "apple with cinnamon",
                ingredients: [new IngredientDto("apple", 2, "big"), new IngredientDto("cinnamon", 20, "grams")],
                steps: ["peel apples", "cut apples", "sprinkle cinnamon"],
                sweet: true,
                category: "SNACK",
                performers: ["Gentle person"]
        ]
        verifyCustomProperties(parameters, defaultParameters.keySet())

        def arguments = defaultParameters + parameters

        return new RecipeRequestDto(
                arguments.name as String,
                arguments.ingredients as List<IngredientDto>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as String,
                arguments.performers as Set<String>
        )
    }

    RecipeDocument sampleRecipeDocument(String id, Map parameters = [:]) {
        Map defaultParameters = [
                name: "apple with cinnamon",
                ingredients: [new IngredientDocument("apple", 2, "big"), new IngredientDocument("cinnamon", 20, "grams")],
                steps: ["peel apples", "cut apples", "sprinkle cinnamon"],
                sweet: true,
                category: "SNACK",
                performers: ["Gentle person"]
        ]
        verifyCustomProperties(parameters, defaultParameters.keySet())

        def arguments = defaultParameters + parameters

        return new RecipeDocument(
                id,
                arguments.name as String,
                arguments.ingredients as List<IngredientDocument>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as String,
                arguments.performers as Set<String>
        )
    }

}