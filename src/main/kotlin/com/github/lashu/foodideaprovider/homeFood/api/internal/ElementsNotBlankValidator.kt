package com.github.lashu.foodideaprovider.homeFood.api.internal

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ElementsNotBlankValidator: ConstraintValidator<ElementsNotBlank, List<String>> {

    override fun isValid(values: List<String>?, context: ConstraintValidatorContext): Boolean {
        return values?.all { it.isNotBlank() } ?: true
    }

}
