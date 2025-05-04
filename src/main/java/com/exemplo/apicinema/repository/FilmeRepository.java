package com.exemplo.apicinema.repository;

import com.exemplo.apicinema.domain.FilmeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeDomain, UUID> {
}
