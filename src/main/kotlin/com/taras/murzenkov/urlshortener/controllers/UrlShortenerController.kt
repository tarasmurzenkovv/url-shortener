package com.taras.murzenkov.urlshortener.controllers

import com.taras.murzenkov.urlshortener.model.ShortUrlRequest
import com.taras.murzenkov.urlshortener.model.ShortUrlResponse
import com.taras.murzenkov.urlshortener.services.UrlShortener
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/url")
class UrlShortenerController(
    private val urlShortSer: UrlShortener
) {
    @GetMapping("/{hash}")
    fun get(@PathVariable hash: String) = urlShortSer.resolve(hash)


    @PostMapping
    @ResponseStatus(CREATED)
    fun shorten(@RequestBody shortUrlRequest: ShortUrlRequest) = ShortUrlResponse(
        shortenedVersion = urlShortSer.encodeAndStore(
            shortUrlRequest.url
        )
    )
}