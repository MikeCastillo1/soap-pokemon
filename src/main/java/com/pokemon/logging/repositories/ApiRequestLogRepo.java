package com.pokemon.logging.repositories;

import com.pokemon.logging.model.ApiRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRequestLogRepo extends JpaRepository<ApiRequestLog, Long> {
}
