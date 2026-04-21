package org.example.tpremise.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RemiseRepository {
    private final JdbcTemplate jdbcTemplate;

    public RemiseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Double getTaux(double montant) {
        return jdbcTemplate.queryForObject(
                "SELECT taux FROM REMISE WHERE ? BETWEEN montant_min AND montant_max LIMIT 1",
                Double.class,
                montant
        );
    }
}
