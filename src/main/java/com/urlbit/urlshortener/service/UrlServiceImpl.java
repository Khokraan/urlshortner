package com.urlbit.urlshortener.service;

import com.urlbit.urlshortener.entity.UrlMapping;
import com.urlbit.urlshortener.exception.UrlNotFoundException;
import com.urlbit.urlshortener.repository.UrlRepository;
import com.urlbit.urlshortener.util.Base62Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public String shortenUrl(String longUrl) {

        UrlMapping urlMapping = new UrlMapping();

        urlMapping.setLongUrl(longUrl);
        urlMapping.setCreatedAt(LocalDateTime.now());

        // save first to generate ID
        UrlMapping savedEntity = urlRepository.save(urlMapping);

        // generate short code using ID
        String shortCode = Base62Encoder.encode(savedEntity.getId());

        savedEntity.setShortCode(shortCode);

        urlRepository.save(savedEntity);

        return "http://localhost:8080/" + shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {

        UrlMapping urlMapping = urlRepository
                .findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));

        return urlMapping.getLongUrl();
    }
}
