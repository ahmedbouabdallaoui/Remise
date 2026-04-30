package org.example.tpremise.service;

import org.example.tpremise.DTO.CreateTransactionDTO;
import org.example.tpremise.exception.RemiseException;
import org.example.tpremise.models.Remise;
import org.example.tpremise.models.Transaction;
import org.example.tpremise.models.Utilisateur;
import org.example.tpremise.repositories.RemiseRepository;
import org.example.tpremise.repositories.TransactionRepository;
import org.example.tpremise.repositories.UtilisateurRepository;
import org.example.tpremise.service.reductionServices.ReductionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final ReductionService reductionService;
    private final RemiseRepository remiseRepository;
    private final TransactionRepository transactionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public TransactionService(@Qualifier("reductionDBService") ReductionService reductionService, RemiseRepository remiseRepository, TransactionRepository transactionRepository, UtilisateurRepository utilisateurRepository) {
        this.reductionService = reductionService;
        this.remiseRepository = remiseRepository;
        this.transactionRepository = transactionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    private void validateMontant(double montant) {
        if (montant <= 0) throw new RemiseException("Le montant doit être supérieur à 0");
        if (montant > 999999) throw new RemiseException("Le montant dépasse le maximum autorisé");
    }

    public Transaction addTransaction(CreateTransactionDTO dto) {
        double montant = dto.getMontant();
        validateMontant(montant);
        double remiseAmount = reductionService.calculerRemise(montant);
        Remise remise = remiseRepository.getRemise(montant);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getUtilisateur().getNom());
        utilisateur.setPrenom(dto.getUtilisateur().getPrenom());
        utilisateurRepository.save(utilisateur);
        Transaction transaction = new Transaction(montant, montant - remiseAmount, remise, utilisateur);
        transactionRepository.addTransaction(transaction);
        return transaction;
    }

    public Transaction getTransactionById(int id) {
        return transactionRepository.getTransactionById(id);
    }

    public Transaction updateTransaction(int id, CreateTransactionDTO dto) {
        double montant = dto.getMontant();
        validateMontant(montant);
        double remiseAmount = reductionService.calculerRemise(montant);
        Remise remise = remiseRepository.getRemise(montant);
        Transaction transaction = transactionRepository.getTransactionById(id);
        transaction.setMontantAvant(montant);
        transaction.setMontantApres(montant - remiseAmount);
        transaction.setRemise(remise);
        transactionRepository.updateTransaction(transaction);
        return transaction;
    }

    public void deleteTransaction(int id) {
        transactionRepository.deleteTransaction(id);
    }
}
