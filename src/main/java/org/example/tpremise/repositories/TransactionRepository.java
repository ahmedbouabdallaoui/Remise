package org.example.tpremise.repositories;

import org.example.tpremise.exception.TransactionNotFoundException;
import org.example.tpremise.mappers.TransactionMapper;
import org.example.tpremise.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
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

    public Transaction getTransactionById(int id) {
        List<Transaction> list = jdbcTemplate.query(
                "SELECT * FROM TRANSACTION WHERE id = ?",
                new TransactionMapper(),
                id
        );

        if (list.isEmpty()) {
            throw new TransactionNotFoundException(id);
        }
        return list.get(0);
    }

    public void updateTransaction(Transaction transaction) {
        int updated = jdbcTemplate.update(
                "UPDATE TRANSACTION SET montant_avant = ?, montant_apres = ?, remise_id = ? WHERE id = ?",
                transaction.getMontantAvant(),
                transaction.getMontantApres(),
                transaction.getRemise().getId(),
                transaction.getId()
        );
        if (updated == 0) {
            throw new TransactionNotFoundException(transaction.getId());
        }
    }

    public void deleteTransaction(int id) {
        int deleted = jdbcTemplate.update("DELETE FROM TRANSACTION WHERE id = ?", id);
        if (deleted == 0) {
            throw new TransactionNotFoundException(id);
        }
    }
}
