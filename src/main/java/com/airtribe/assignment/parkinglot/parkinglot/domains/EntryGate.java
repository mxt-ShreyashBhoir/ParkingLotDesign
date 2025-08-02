package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.*;

/**
 * Configuration for an entry gate, specifying the strategy type.
 * Actual ParkingStrategy is dynamically resolved from strategyType.
 */
@Entity
@SequenceGenerator(name = "entry_gate_seq", sequenceName = "entry_gate_seq", allocationSize = 1)
public class EntryGate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_gate_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ParkingStrategyType strategyType;

    public enum ParkingStrategyType {
        RANDOM, FIFO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingStrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(ParkingStrategyType strategyType) {
        this.strategyType = strategyType;
    }
}

