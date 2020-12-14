package com.github.lashu.foodideaprovider.homeFood.recipe

import java.lang.RuntimeException

class RecipeNotFoundException(id: String): RuntimeException("Recipe with id: $id not found")