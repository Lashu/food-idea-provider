package com.github.lashu.foodideaprovider.homeFood.recipe

import java.util.UUID.randomUUID

interface RecipeIdGenerator {

    fun generate(): String

}

class DefaultRecipeIdGenerator: RecipeIdGenerator {

    override fun generate(): String {
        return randomUUID().toString()
    }

}