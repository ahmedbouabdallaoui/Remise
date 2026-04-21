package org.example.tpremise.repositories;

import org.example.tpremise.mappers.TransactionMapper;
import org.example.tpremise.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTransaction(Transaction transaction) {
        jdbcTemplate.update(
                "INSERT INTO TRANSACTION (date, montant_avant, montant_apres, remise_id) VALUES (?, ?, ?, ?)",
                transaction.getDate(),
                transaction.getMontantAvant(),
                transaction.getMontantApres(),
                transaction.getRemise().getId()
        );
    }

    public Transaction getTransactionById(String id) {
        List<Transaction> list = jdbcTemplate.query(
                "SELECT * FROM TRANSACTION WHERE id = ?",
                new TransactionMapper(),
                id
        );

        if (list.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        return list.get(0);
    }
}
