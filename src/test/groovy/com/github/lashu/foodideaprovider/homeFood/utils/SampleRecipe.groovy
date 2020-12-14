package com.github.lashu.foodideaprovider.homeFood.utils

import com.github.lashu.foodideaprovider.homeFood.api.CreateRecipeRequestDto
import com.github.lashu.foodideaprovider.homeFood.api.IngredientDto
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.IngredientDocument
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.RecipeDocument

trait SampleRecipe {

    CreateRecipeRequestDto sampleCreateRecipeRequest() {
        return new CreateRecipeRequestDto(
                "apple with cinnamon",
                [new IngredientDto("apple", 2, "big"), new IngredientDto("cinnamon", 20, "grams")],
                ["peel apples", "cut apples", "sprinkle cinnamon"],
                true,
                "SNACK",
                ["Gentle person"]
        )
    }

    RecipeDocument sampleRecipeDocument(String id) {
        return new RecipeDocument(
                id,
                "apple with cinnamon",
                [new IngredientDocument("apple", 2, "big"), new IngredientDocument("cinnamon", 20, "grams")],
                ["peel apples", "cut apples", "sprinkle cinnamon"],
                true,
                "SNACK",
                ["Gentle person"]
        )
    }

}