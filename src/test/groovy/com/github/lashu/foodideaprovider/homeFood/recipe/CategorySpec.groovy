package com.github.lashu.foodideaprovider.homeFood.recipe

import spock.lang.Specification
import spock.lang.Unroll

import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.BREAKFAST
import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.DINNER
import static com.github.lashu.foodideaprovider.homeFood.recipe.Category.SNACK

class CategorySpec extends Specification {

    @Unroll
    def "should parse name #name for category #category "() {
        when:
            def result = Category.fromName(name)

        then:
            result == category

        where:
            name        || category
            "snack"     || SNACK
            "diNNeR"    || DINNER
            "BREAKFAST" || BREAKFAST
    }

    def "should throw InvalidCategoryException when parsing non existing category name"() {
        given:
            def categoryName = "nonExistingCategory"

        when:
            Category.fromName(categoryName)

        then:
            def exception = thrown(InvalidCategoryException)
            exception.message == "Category with name: $categoryName not found"
    }

}
