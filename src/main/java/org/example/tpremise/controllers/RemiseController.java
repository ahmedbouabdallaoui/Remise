package org.example.tpremise.controllers;

import org.example.tpremise.DTO.CreateTransactionDTO;
import org.example.tpremise.models.Transaction;
import org.example.tpremise.repositories.TransactionRepository;
import org.example.tpremise.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RemiseController {
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public RemiseController(TransactionService transactionService,  TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transaction")
    public String remise(@RequestBody CreateTransactionDTO dto)  {
        transactionService.addTransaction(dto);
        return  "success";
    }

    @GetMapping("/transaction/{id}")
    public Transaction get(@PathVariable String id) {
        return transactionRepository.getTransactionById(id);
    }
}
