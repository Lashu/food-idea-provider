package com.github.lashu.foodideaprovider.homeFood.api

import com.github.lashu.foodideaprovider.homeFood.base.SampleRecipe
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import java.util.stream.Collectors

class RecipeRequestDtoSpec extends Specification implements SampleRecipe {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    def "should accept minimal recipe request"() {
        given:
            def recipeRequest = sampleRecipeRequest([
                    name: "Nutella bananas",
                    category: "snack",
                    ingredients: null,
                    steps: null,
                    performers: null
            ])

        when:
            def violations = validator.validate(recipeRequest)

        then:
            violations.isEmpty()
    }

    def "should not allow recipe request with invalid values"() {
        given:
            def recipeRequest = sampleRecipeRequest([
                    name: "",
                    category: "snack",
                    ingredients: [new IngredientDto("", -1.0, "")],
                    steps: ["", "fry"],
                    performers: []
            ])

        when:
            def violations = validator.validate(recipeRequest)

        then:
            violations.stream()
                .map({ violation -> violation.propertyPath.toString() + " " + violation.message } )
                .collect(Collectors.toSet()) == Set.of(
                    "name can not be blank",
                    "ingredients[0].name can not be blank",
                    "ingredients[0].amount must be positive",
                    "ingredients[0].unit can not be blank",
                    "steps can not have blank elements"
            )
    }

}
