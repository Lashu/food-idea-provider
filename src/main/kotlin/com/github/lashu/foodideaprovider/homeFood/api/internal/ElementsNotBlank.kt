package com.github.lashu.foodideaprovider.homeFood.api.internal

import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [ElementsNotBlankValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class ElementsNotBlank(
    val message: String = "can not have blank elements",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
