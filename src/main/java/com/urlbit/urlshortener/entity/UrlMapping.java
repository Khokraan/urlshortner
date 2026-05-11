package com.urlbit.urlshortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mapping")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortCode;

    @Column(columnDefinition = "TEXT")
    private String longUrl;

    private LocalDateTime createdAt;

    private LocalDateTime expiry;
}
