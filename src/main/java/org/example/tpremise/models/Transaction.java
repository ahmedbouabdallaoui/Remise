package org.example.tpremise.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "montant_avant", nullable = false)
    private double montantAvant;

    @Column(name = "montant_apres", nullable = false)
    private double montantApres;

    @ManyToOne
    @JoinColumn(name = "remise_id")
    private Remise remise;
}
