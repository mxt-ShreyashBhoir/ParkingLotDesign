package com.airtribe.assignment.parkinglot.parkinglot.domains;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Cashless (e.g., card/UPI) payment strategy.
 */
@Component
public class CashlessPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        return Math.max(25, hours * 25); // ₹25/hr base rate for cashless
    }

    @Override
    public boolean supports(ExitGate.PaymentStrategyType type) {
        return type == ExitGate.PaymentStrategyType.CASHLESS;
    }

    @Override
    public double pay(long hoursParked) {
        double rate = 18.0; // Slight discount
        return hoursParked * rate;
    }
}
