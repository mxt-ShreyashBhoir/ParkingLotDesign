package com.airtribe.assignment.parkinglot.parkinglot.controllers;

import com.airtribe.assignment.parkinglot.parkinglot.domains.PaymentStrategy;
import com.airtribe.assignment.parkinglot.parkinglot.services.ExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exit")
public class ExitController {

    @Autowired
    private ExitService exitService;

    @Autowired
    private List<PaymentStrategy> strategies;

    @PostMapping("/{ticketId}")
    public ResponseEntity<String> exit(@PathVariable Long ticketId,
                                       @RequestParam(defaultValue = "cash") String paymentMode) throws Throwable {
        PaymentStrategy strategy = strategies.stream()
                .filter(s -> s.getClass().getSimpleName().toLowerCase().contains(paymentMode.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Payment strategy not found"));

        double paid = exitService.processExit(ticketId, strategy);
        return ResponseEntity.ok("Payment ₹" + paid + " done using " + strategy.getClass().getSimpleName());
    }
}
