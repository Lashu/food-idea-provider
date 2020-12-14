package com.github.lashu.foodideaprovider.homeFood.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore

@Controller
@RequestMapping("/")
class SwaggerRedirectEndpoint {

    @ApiIgnore
    @GetMapping("/")
    fun swaggerUi(): String {
        return "redirect:/swagger-ui.html"
    }

}