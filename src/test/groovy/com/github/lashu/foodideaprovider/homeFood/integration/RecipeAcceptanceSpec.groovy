package com.github.lashu.foodideaprovider.homeFood.integration


import com.github.lashu.foodideaprovider.homeFood.api.RecipeResponseDto
import com.github.lashu.foodideaprovider.homeFood.api.RecipesResponseDto
import com.github.lashu.foodideaprovider.homeFood.infrastructure.persistence.MongoRecipeSpringRepository
import com.github.lashu.foodideaprovider.homeFood.base.SampleRecipe
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class RecipeAcceptanceSpec extends IntegrationSpec implements SampleRecipe {

    @Autowired
    MongoRecipeSpringRepository mongoRecipeSpringRepository

    def setup() {
        mongoRecipeSpringRepository.deleteAll()
    }

    def "should create recipe"() {
        given:
            def requestBody = toJson(sampleRecipeRequest())

        when:
            def response = post(localUrl("/api/recipes"), requestBody, RecipeResponseDto)

        then:
            response.statusCode == CREATED
    }

    def "should get existing recipe"() {
        given:
            def recipeId = "recipeId"
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(recipeId))

        when:
            def response = get(localUrl("/api/recipes/$recipeId"), RecipeResponseDto)

        then:
            response.statusCode == OK
            response.body.id == recipeId
    }

    def "should get all existing recipes"() {
        given:
            mongoRecipeSpringRepository.insert(sampleRecipeDocument("recipeId1"))
            mongoRecipeSpringRepository.insert(sampleRecipeDocument("recipeId2"))

        when:
            def response = get(localUrl("/api/recipes"), RecipesResponseDto)

        then:
            response.statusCode == OK
            response.body.recipes.size() == 2
    }

    def "should update recipe"() {
        given:
            def recipeId = "recipeId"
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(recipeId))

            def requestBody = toJson(sampleRecipeRequest([name : "new recipe name"]))

        when:
            def response = put(localUrl("/api/recipes/$recipeId"), requestBody)

        then:
            response.statusCode == NO_CONTENT
            with(mongoRecipeSpringRepository.findById(recipeId).get()) {
                id == recipeId
                name == "new recipe name"
            }
    }

    def "should delete recipe"() {
        given:
            def recipeId = "recipeId"
            mongoRecipeSpringRepository.insert(sampleRecipeDocument(recipeId))

        when:
            def response = delete(localUrl("/api/recipes/$recipeId"))

        then:
            response.statusCode == OK
            mongoRecipeSpringRepository.findById(recipeId).isEmpty()
    }

}
