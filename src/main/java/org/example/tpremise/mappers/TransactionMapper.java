package org.example.tpremise.mappers;

import org.example.tpremise.models.Transaction;
import org.example.tpremise.models.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
        transaction.setMontantAvant(rs.getDouble("montant_avant"));
        transaction.setMontantApres(rs.getDouble("montant_apres"));

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(rs.getInt("utilisateur_id"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        transaction.setUtilisateur(utilisateur);

        return transaction;
    }
}
