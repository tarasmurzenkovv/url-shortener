package com.taras.murzenkov.urlshortener

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.*
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.data.redis.core.RedisTemplate
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(PER_CLASS)
class UrlShortenerApplicationTests {
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @LocalServerPort
    var port: Int = 0

    @BeforeAll
    fun setUp() {
        RestAssured.port = port
        redisTemplate.opsForValue().set("hash", "full-url")
    }

    companion object {
        @Container
        @JvmStatic
        val redis = GenericContainer("redis:6-alpine")
            .withExposedPorts(6379)
    }


    @Test
    fun shouldShortenUrl() {
        RestAssured.given()
            .contentType("application/json")
            .body(
                """
                {
                  "url": "google.com"
                }
                """
            )
            .post("/api/v1/url")
            .then()
            .statusCode(201)
            .body("shortenedVersion", equalTo("Z29vZ2xlLmNvbQ=="))
    }

    @Test
    fun shouldGetFullUrl() {
        Assertions.assertEquals(
            RestAssured.given()
                .get("/api/v1/url/hash")
                .then()
                .statusCode(200).extract().body().asString(), "full-url"
        )
    }
}
