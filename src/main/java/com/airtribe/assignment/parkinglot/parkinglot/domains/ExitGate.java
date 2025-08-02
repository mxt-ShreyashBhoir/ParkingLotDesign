package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.*;

/**
 * Configuration for an exit gate and its payment strategy.
 */
@Entity
@SequenceGenerator(name = "exit_gate_seq", sequenceName = "exit_gate_seq", allocationSize = 1)
public class ExitGate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exit_gate_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStrategyType paymentStrategy;

    public enum PaymentStrategyType {
        CASH, CASHLESS, COUPON
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStrategyType getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategyType paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

