package org.example.tpremise.controllers;

import org.example.tpremise.DTO.CreateTransactionDTO;
import org.example.tpremise.models.Transaction;
import org.example.tpremise.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody CreateTransactionDTO dto) {
        Transaction transaction = transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody CreateTransactionDTO dto) {
        transactionService.updateTransaction(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
