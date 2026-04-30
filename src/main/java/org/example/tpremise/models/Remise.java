package org.example.tpremise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "REMISE")
public class Remise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "montant_min", nullable = false)
    private double montantMin;

    @Column(name = "montant_max", nullable = false)
    private double montantMax;

    @Column(name = "taux", nullable = false)
    private double taux;
}
