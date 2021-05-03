package com.github.lashu.foodideaprovider.homeFood.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

import static org.springframework.http.HttpMethod.DELETE
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT

trait Rest {

    @Autowired
    TestRestTemplate restTemplate

    def <T> ResponseEntity<T> post(String url, String requestBody, Class<T> responseType) {
        restTemplate.exchange(url, POST, new HttpEntity(requestBody, new HttpHeaders(["Content-Type": "application/json"])), responseType)
    }

    def <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        restTemplate.exchange(url, GET, new HttpEntity(new HttpHeaders(["Accept": "application/json"])), responseType)
    }

    ResponseEntity<Void> put(String url, String requestBody) {
        restTemplate.exchange(url, PUT, new HttpEntity(requestBody, new HttpHeaders(["Content-Type": "application/json"])), Void)
    }

    ResponseEntity<Void> delete(String url) {
        restTemplate.exchange(url, DELETE, null, Void)
    }

}