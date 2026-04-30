package org.example.tpremise.repositories;

import org.example.tpremise.models.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UtilisateurRepository {
    private final JdbcTemplate jdbcTemplate;

    public UtilisateurRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Utilisateur save(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO UTILISATEUR (nom, prenom) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            return ps;
        }, keyHolder);
        utilisateur.setId(keyHolder.getKey().intValue());
        return utilisateur;
    }
}
