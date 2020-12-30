package com.github.lashu.foodideaprovider.homeFood.api.internal

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ElementsNotBlankValidator: ConstraintValidator<ElementsNotBlank, Iterable<String>> {

    override fun isValid(values: Iterable<String>?, context: ConstraintValidatorContext): Boolean {
        return values?.all { it.isNotBlank() } ?: true
    }

}
