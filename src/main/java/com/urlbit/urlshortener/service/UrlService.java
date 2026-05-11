package com.urlbit.urlshortener.service;

public interface UrlService {
    String shortenUrl(String longUrl);

    String getOriginalUrl(String shortCode);
}
