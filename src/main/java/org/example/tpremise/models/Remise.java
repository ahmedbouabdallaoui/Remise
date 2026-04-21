package org.example.tpremise.models;

import jakarta.persistence.*;

@Entity
@Table(name = "REMISE")
public class Remise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant_min", nullable = false)
    private double montantMin;

    @Column(name = "montant_max", nullable = false)
    private double montantMax;

    @Column(name = "taux", nullable = false)
    private double taux;

    // getters & setters
}
