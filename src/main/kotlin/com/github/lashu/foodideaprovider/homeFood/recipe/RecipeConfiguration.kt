package com.github.lashu.foodideaprovider.homeFood.recipe

import com.github.lashu.foodideaprovider.homeFood.recipe.internal.RecipeFacadeImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecipeConfiguration {

    @Bean
    fun recipeFacade(recipeRepository: RecipeRepository, recipeIdGenerator: RecipeIdGenerator): RecipeFacade {
        return RecipeFacadeImpl(recipeRepository, recipeIdGenerator)
    }

    @Bean
    fun recipeIdGenerator(): RecipeIdGenerator {
        return DefaultRecipeIdGenerator()
    }

}
