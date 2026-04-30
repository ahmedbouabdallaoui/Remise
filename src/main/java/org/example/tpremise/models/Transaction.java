package org.example.tpremise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "montant_avant", nullable = false)
    private double montantAvant;

    @Column(name = "montant_apres", nullable = false)
    private double montantApres;

    @ManyToOne
    @JoinColumn(name = "remise_id")
    private Remise remise;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Transaction() {}

    public Transaction(double montantAvant, double montantApres, Remise remise, Utilisateur utilisateur) {
        this.setDate(java.time.LocalDateTime.now());
        this.setMontantAvant(montantAvant);
        this.setMontantApres(montantApres);
        this.setRemise(remise);
        this.setUtilisateur(utilisateur);
    }
}
