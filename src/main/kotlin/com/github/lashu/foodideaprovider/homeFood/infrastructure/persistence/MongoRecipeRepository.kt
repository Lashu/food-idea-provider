package com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence

import com.github.lashu.foodideaprovider.homeFood.recipe.Category
import com.github.lashu.foodideaprovider.homeFood.recipe.Ingredient
import com.github.lashu.foodideaprovider.homeFood.recipe.Recipe
import com.github.lashu.foodideaprovider.homeFood.recipe.RecipeRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MongoRecipeSpringRepository: MongoRepository<RecipeDocument, String>

@Repository
class MongoRecipeRepository(private val mongoRecipeSpringRepository: MongoRecipeSpringRepository): RecipeRepository {

    override fun save(recipe: Recipe): Recipe {
        return mongoRecipeSpringRepository.save(recipe.toDocument()).toRecipe()
    }

    override fun findById(id: String): Recipe? {
        return mongoRecipeSpringRepository.findById(id).map { it.toRecipe() }.orNull()
    }

    override fun findAll(): List<Recipe> {
        return mongoRecipeSpringRepository.findAll().map { it.toRecipe() }
    }

    override fun deleteById(id: String) {
        return mongoRecipeSpringRepository.deleteById(id)
    }

    private fun Recipe.toDocument(): RecipeDocument = RecipeDocument(
        id,
        name,
        ingredients?.map { IngredientDocument(it.name, it.amount, it.unit) },
        steps,
        sweet,
        category.name,
        performers
    )

    private fun RecipeDocument.toRecipe(): Recipe = Recipe(
        id,
        name,
        ingredients?.map { Ingredient(it.name, it.amount, it.unit) },
        steps,
        sweet,
        Category.fromName(category),
        performers
    )

    private fun <T> Optional<T>.orNull(): T? = orElse(null)

}
