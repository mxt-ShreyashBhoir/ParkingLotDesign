package com.airtribe.assignment.parkinglot.parkinglot.domains;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Cash-based payment strategy.
 */
@Component
public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        return Math.max(20, hours * 20); // ₹20/hr base rate
    }

    @Override
    public boolean supports(ExitGate.PaymentStrategyType type) {
        return type == ExitGate.PaymentStrategyType.CASH;
    }

    @Override
    public double pay(long hoursParked) {
        double rate = 20.0; // Rs. per hour
        return hoursParked * rate;
    }
}
