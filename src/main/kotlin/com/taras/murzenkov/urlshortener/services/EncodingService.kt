package com.taras.murzenkov.urlshortener.services

import org.springframework.stereotype.Service
import java.util.Base64

@Service
class EncodingService {
    fun encode(url: String): String = Base64.getEncoder().encodeToString(url.toByteArray())
}