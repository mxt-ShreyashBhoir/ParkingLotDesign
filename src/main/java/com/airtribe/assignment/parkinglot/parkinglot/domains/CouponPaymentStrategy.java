package com.airtribe.assignment.parkinglot.parkinglot.domains;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Coupon-based free or discounted exit.
 */
@Component
public class CouponPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime) {
        return 0.0; // Assume coupon gives full discount
    }

    @Override
    public boolean supports(ExitGate.PaymentStrategyType type) {
        return type == ExitGate.PaymentStrategyType.COUPON;
    }

    @Override
    public double pay(long hoursParked) {
        double baseRate = 20.0;
        double total = hoursParked * baseRate;
        double discount = 0.5; // 50% off
        return total * discount;
    }
}

