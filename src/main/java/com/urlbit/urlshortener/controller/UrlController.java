package com.urlbit.urlshortener.controller;

import com.urlbit.urlshortener.dto.ShortenUrlRequest;
import com.urlbit.urlshortener.dto.ShortenUrlResponse;
import com.urlbit.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/api/v1/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(
            @Valid @RequestBody ShortenUrlRequest request) {

        String shortUrl =
                urlService.shortenUrl(request.getLongUrl());

        return ResponseEntity.ok(
                new ShortenUrlResponse(shortUrl)
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                urlService.getOriginalUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(
                headers,
                HttpStatus.FOUND
        );
    }
}
