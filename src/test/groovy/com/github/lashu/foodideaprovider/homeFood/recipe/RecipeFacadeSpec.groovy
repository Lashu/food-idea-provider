package com.github.lashu.foodideaprovider.homeFood.recipe

import com.github.lashu.foodideaprovider.homeFood.base.SampleRecipe
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.InMemoryRecipeRepository
import spock.lang.Specification
import spock.lang.Subject

import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.BREAKFAST
import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.SNACK
import static java.util.stream.Collectors.toList

class RecipeFacadeSpec extends Specification implements SampleRecipe {

    @Subject
    RecipeFacade recipeFacade

    def setup() {
        def recipeIdGenerator = Stub(RecipeIdGenerator) {
            generate() >>> ["recipeId1", "recipeId2"]

        }
        recipeFacade = new RecipeConfiguration().recipeFacade(new InMemoryRecipeRepository(), recipeIdGenerator)
    }

    def "should create recipe"() {
        when:
            def result = recipeFacade.createRecipe(sampleCreateRecipeRequest())

        then:
            recipeFacade.getRecipe(result.id)
    }

    def "should get recipe"() {
        given:
            recipeFacade.createRecipe(sampleCreateRecipeRequest())

        when:
            def result = recipeFacade.getRecipe("recipeId1")

        then:
            result
            result.id == "recipeId1"
    }

    def "should get recipes"() {
        given:
            recipeFacade.createRecipe(sampleCreateRecipeRequest())
            recipeFacade.createRecipe(sampleCreateRecipeRequest())

        when:
            def result = recipeFacade.getRecipes()

        then:
            result.size() == 2
            result.stream().map({ it.id }).collect(toList()) == ["recipeId1", "recipeId2"]
    }

    def "should delete recipe"() {
        given:
            recipeFacade.createRecipe(sampleCreateRecipeRequest())

        when:
            recipeFacade.deleteRecipe("recipeId1")

        then:
            recipeFacade.getRecipes().size() == 0
    }

    def "should update recipe"() {
        given:
            recipeFacade.createRecipe(sampleCreateRecipeRequest([category: SNACK]))

        when:
            recipeFacade.updateRecipe(sampleUpdateRecipeRequest([category: BREAKFAST]))

        then:
            recipeFacade.getRecipe("recipeId1").category == BREAKFAST
    }

    def "should throw RecipeNotFoundException when getting non existing recipe"() {
        when:
            recipeFacade.getRecipe("nonExistingRecipeId")

        then:
            thrown(RecipeNotFoundException)
    }

}
