package com.taras.murzenkov.urlshortener.dao

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository

@Repository
class UrlStorage(
    private val redisTemplate: RedisTemplate<String, String>,
    private val valueOperations: ValueOperations<String, String> = redisTemplate.opsForValue()
) {
    fun get(hash: String): String? = valueOperations.get(hash)

    fun put(hash: String, url: String) {
        valueOperations.set(hash, url)
    }
}