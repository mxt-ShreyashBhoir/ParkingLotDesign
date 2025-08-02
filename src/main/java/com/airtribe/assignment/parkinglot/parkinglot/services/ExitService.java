package com.airtribe.assignment.parkinglot.parkinglot.services;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingSpace;
import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingTicket;
import com.airtribe.assignment.parkinglot.parkinglot.domains.PaymentStrategy;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExitService {

    private final ParkingTicketRepository ticketRepository;
    private final ParkingSpaceRepository spaceRepository;

    @Autowired
    public ExitService(ParkingTicketRepository ticketRepository,
                       ParkingSpaceRepository spaceRepository) {
        this.ticketRepository = ticketRepository;
        this.spaceRepository = spaceRepository;
    }

    /**
     * Processes vehicle exit using the selected payment strategy.
     * @param ticketId Parking ticket ID
     * @param paymentStrategy Strategy used for payment calculation
     * @return Payment amount
     * @throws IllegalArgumentException if ticket or space not found
     */
    public double processExit(Long ticketId, PaymentStrategy paymentStrategy) throws Throwable {
        Optional<ParkingTicket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }

        ParkingTicket ticket = optionalTicket.get();
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hoursParked = Math.max(duration.toHours(), 1); // Minimum 1 hour charge

        double fee = paymentStrategy.pay(hoursParked);
        System.out.println("Payment of ₹" + fee + " completed using " + paymentStrategy.getClass().getSimpleName());

        // Free the parking space
        ParkingSpace space = (ParkingSpace) spaceRepository.findById(ticket.getParkingSpaceId())
                .orElseThrow(() -> new IllegalArgumentException("Parking space not found"));

        space.setOccupied(false);
        space.setParkedVehicle(null);
        spaceRepository.save(space);

        // Remove the ticket from active DB (optional: archive it)
        ticketRepository.delete(ticket);

        return fee;
    }
}