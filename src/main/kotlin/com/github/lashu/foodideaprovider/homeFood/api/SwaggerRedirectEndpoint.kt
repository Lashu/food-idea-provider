package com.github.lashu.foodideaprovider.homeFood.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore

@Controller
class SwaggerRedirectEndpoint {

    @ApiIgnore
    @RequestMapping
    fun swaggerUi(): String {
        return "redirect:/swagger-ui.html"
    }

}