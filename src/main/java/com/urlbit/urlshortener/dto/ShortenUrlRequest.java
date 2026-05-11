package com.urlbit.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShortenUrlRequest {
    @NotBlank(message = "URL cannot be empty")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Invalid URL format"
    )
    private String longUrl;
}
