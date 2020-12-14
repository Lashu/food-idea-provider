package com.github.lashu.foodideaprovider.homeFood

import com.github.lashu.foodideaprovider.AppRunner
import com.github.lashu.foodideaprovider.homeFood.utils.Rest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(
        classes = AppRunner,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
class IntegrationSpec extends Specification implements Rest {

    @LocalServerPort
    protected int port

    protected String localUrl(String endpoint) {
        return "http://localhost:$port$endpoint"
    }

}
