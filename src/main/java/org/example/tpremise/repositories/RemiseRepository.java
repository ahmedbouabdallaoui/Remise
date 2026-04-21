package org.example.tpremise.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RemiseRepository {
    private final JdbcTemplate jdbcTemplate;

    public RemiseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Double> getTaux(double montant) {
        return jdbcTemplate.query(
                "SELECT taux FROM REMISE WHERE ? BETWEEN montant_min AND montant_max LIMIT 1",
                (rs, rowNum) -> rs.getDouble("taux"),
                montant
        );
    }

}
