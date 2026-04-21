package org.example.tpremise.service;

import org.example.tpremise.DTO.CreateTransactionDTO;
import org.example.tpremise.models.Remise;
import org.example.tpremise.models.Transaction;
import org.example.tpremise.repositories.RemiseRepository;
import org.example.tpremise.repositories.TransactionRepository;
import org.example.tpremise.service.reductionServices.ReductionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final ReductionService reductionService;
    private final RemiseRepository remiseRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(@Qualifier("reductionDBService") ReductionService reductionService, RemiseRepository remiseRepository, TransactionRepository transactionRepository) {
        this.reductionService = reductionService;
        this.remiseRepository = remiseRepository;
        this.transactionRepository = transactionRepository;

    }

    public void addTransaction(CreateTransactionDTO dto) {
        double montant = dto.getMontant();
        double apresRemise = reductionService.calculerRemise(montant);
        Remise remise = remiseRepository.getRemise(montant);
        Transaction transaction =  new Transaction(montant, apresRemise, remise);
        transactionRepository.addTransaction(transaction);
    }
}
