package com.taras.murzenkov.urlshortener.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfiguration {
    @Bean
    fun jedisConnectionFactory() = JedisConnectionFactory()

    @Bean
    fun redisTemplate() = with(RedisTemplate<String, String>()) {
        connectionFactory = jedisConnectionFactory()
        this
    }
}