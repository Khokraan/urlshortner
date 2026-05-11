package com.urlbit.urlshortener.repository;

import com.urlbit.urlshortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
Why Spring Data removes the need @Repository?
Repository Proxy Pattern

Spring:

scans interfaces extending JpaRepository
creates implementation dynamically
injects it as bean*/
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortCode(String shortCode);
}