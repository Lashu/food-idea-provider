package com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recipes")
data class RecipeDocument(
    @Id val id: String,
    val name: String,
    val ingredients: List<IngredientDocument>,
    val steps: List<String>,
    val sweet: Boolean,
    val category: String,
    val performers: List<String>?
)

data class IngredientDocument(
    val name: String,
    val amount: Double,
    val unit: String
)
