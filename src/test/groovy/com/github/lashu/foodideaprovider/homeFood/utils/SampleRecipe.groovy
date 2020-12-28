package com.github.lashu.foodideaprovider.homeFood.utils

import com.github.lashu.foodideaprovider.homeFood.api.RecipeRequestDto
import com.github.lashu.foodideaprovider.homeFood.api.IngredientDto
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.IngredientDocument
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.RecipeDocument

trait SampleRecipe {

    RecipeRequestDto sampleRecipeRequest(Map parameters = [:]) {
        Map defaultParameters = [
                name: "apple with cinnamon",
                ingredients: [new IngredientDto("apple", 2, "big"), new IngredientDto("cinnamon", 20, "grams")],
                steps: ["peel apples", "cut apples", "sprinkle cinnamon"],
                sweet: true,
                category: "SNACK",
                performers: ["Gentle person"]
        ]

        def arguments = defaultParameters + parameters

        return new RecipeRequestDto(
                arguments.name as String,
                arguments.ingredients as List<IngredientDto>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as String,
                arguments.performers as List<String>
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

        def arguments = defaultParameters + parameters

        return new RecipeDocument(
                id,
                arguments.name as String,
                arguments.ingredients as List<IngredientDocument>,
                arguments.steps as List<String>,
                arguments.sweet as Boolean,
                arguments.category as String,
                arguments.performers as List<String>
        )
    }

}