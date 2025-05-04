package com.exemplo.apicinema.repository;

import com.exemplo.apicinema.domain.DiretorDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DiretorRepository extends JpaRepository<DiretorDomain, UUID> {
}
