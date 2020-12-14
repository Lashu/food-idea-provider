package com.github.lashu.foodideaprovider.homeFood.api

import com.github.lashu.foodideaprovider.homeFood.recipe.Category
import com.github.lashu.foodideaprovider.homeFood.recipe.CreateRecipeRequest
import com.github.lashu.foodideaprovider.homeFood.recipe.Ingredient
import com.github.lashu.foodideaprovider.homeFood.recipe.Recipe
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeFacade
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipeEndpoint(private val recipeFacade: RecipeFacade) {

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createRecipe(@RequestBody createRecipeRequest: CreateRecipeRequestDto): RecipeResponseDto {
        return recipeFacade.createRecipe(createRecipeRequest.toDomain()).toResponse()
    }

    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun getRecipe(@PathVariable id: String): RecipeResponseDto {
        return recipeFacade.getRecipe(id).toResponse()
    }

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun getRecipes(): RecipesResponseDto {
        return RecipesResponseDto(recipeFacade.getRecipes().map { it.toResponse() })
    }

    private fun Recipe.toResponse(): RecipeResponseDto = RecipeResponseDto(
        id,
        name,
        ingredients.map { IngredientDto(it.name, it.amount, it.unit) },
        steps,
        sweet,
        category.name,
        performers
    )

    private fun CreateRecipeRequestDto.toDomain(): CreateRecipeRequest = CreateRecipeRequest(
        name,
        ingredients.map { Ingredient(it.name, it.amount, it.unit) },
        steps,
        sweet,
        Category.valueOf(category),
        performers
    )

}