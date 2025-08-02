package com.airtribe.assignment.parkinglot.parkinglot.domains;

import java.time.LocalDateTime;

/**
 * Strategy interface for calculating payments on exit.
 */
public interface PaymentStrategy {
    double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime);
    double pay(long hoursParked);
    boolean supports(ExitGate.PaymentStrategyType type);
}

