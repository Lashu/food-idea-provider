package com.github.lashu.foodideaprovider.homeFood

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.lashu.foodideaprovider.AppRunner
import com.github.lashu.foodideaprovider.homeFood.utils.Rest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(
        classes = AppRunner,
        properties = "application.environment=integration",
        webEnvironment = RANDOM_PORT
)
class IntegrationSpec extends Specification implements Rest {

    @LocalServerPort
    protected int port

    @Autowired
    protected ObjectMapper objectMapper

    protected String localUrl(String endpoint) {
        return "http://localhost:$port$endpoint"
    }

    protected String toJson(Object object) {
        return objectMapper.writeValueAsString(object)
    }

}
