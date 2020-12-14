package com.github.lashu.foodideaprovider.homeFood.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

trait Rest {

    @Autowired
    TestRestTemplate restTemplate

    def <T> ResponseEntity<T> post(String url, Object request, Class<T> responseType) {
        restTemplate.postForEntity(url, new HttpEntity(request, new HttpHeaders(["Content-Type": "application/json"])), responseType)
    }

    def <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(
                new HttpHeaders(["Accept": "application/json"])), responseType)
    }

}