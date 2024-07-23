package com.taras.murzenkov.urlshortener.services

import com.taras.murzenkov.urlshortener.dao.UrlStorage
import org.springframework.stereotype.Service

@Service
class UrlShortener(
    private val urlStorage: UrlStorage,
    private val encodingService: EncodingService
) {
    fun resolve(encodedValue: String): String? = urlStorage.get(encodedValue)

    fun encodeAndStore(url: String) = with(encodingService.encode(url)) {
        urlStorage.put(this, url)
        this
    }
}