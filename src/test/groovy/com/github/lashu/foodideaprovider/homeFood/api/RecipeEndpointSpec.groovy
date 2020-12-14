package com.github.lashu.foodideaprovider.homeFood.api

import com.github.lashu.foodideaprovider.homeFood.IntegrationSpec
import com.github.lashu.foodideaprovider.homeFood.api.internal.ErrorHolder
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.MongoRecipeSpringRepository
import com.github.lashu.foodideaprovider.homeFood.utils.SampleRecipe
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class RecipeEndpointSpec extends IntegrationSpec implements SampleRecipe {

    @Autowired
    MongoRecipeSpringRepository mongoRecipeSpringRepository

    def setup() {
        mongoRecipeSpringRepository.deleteAll()
    }

    def "should create recipe"() {
        given:
            def request = sampleCreateRecipeRequest()

        when:
            def response = post(localUrl("/recipes"), request, RecipeResponseDto)

        then:
            response.statusCode == CREATED
    }

    def "should return NOT_FOUND when getting non existing recipe"() {
        given:
            def nonExistingRecipeId = "recipeId"

        when:
            def response = get(localUrl("/recipes/$nonExistingRecipeId"), ErrorHolder)

        then:
            response.statusCode == NOT_FOUND
            response.body.error.message == "Recipe with id: $nonExistingRecipeId not found"
    }

    def "should get existing recipe"() {
        given:
            def recipeId = "recipeId"
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(recipeId))

        when:
            def response = get(localUrl("/recipes/$recipeId"), RecipeResponseDto)

        then:
            response.statusCode == OK
            response.body.id == recipeId
    }

    def "should get all existing recipes"() {
        given:
            def firstRecipeId = "recipeId1"
            def secondRecipeId = "recipeId2"
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(firstRecipeId))
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(secondRecipeId))

        when:
            def response = get(localUrl("/recipes"), RecipesResponseDto)

        then:
            response.statusCode == OK
            response.body.recipes.size() == 2
    }

}
