package com.airtribe.assignment.parkinglot.parkinglot.services;

import com.airtribe.assignment.parkinglot.parkinglot.domains.*;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingLotRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingTicketRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entry service handles incoming vehicle requests.
 * Resolves parking spot using the assigned strategy and creates a ticket.
 */
@Service
public class EntryService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingTicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository spaceRepository;

    private final List<ParkingStrategy> strategies;

    @Autowired
    public EntryService(ParkingLotRepository parkingLotRepository,
                        ParkingTicketRepository ticketRepository,
                        VehicleRepository vehicleRepository,
                        ParkingSpaceRepository spaceRepository,
                        List<ParkingStrategy> strategies) {
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
        this.spaceRepository = spaceRepository;
        this.strategies = strategies;
    }

    @Transactional
    public ParkingTicket processEntry(Vehicle vehicle) {

        List<ParkingLot> lot = parkingLotRepository.findAll(); // Simplified
        if (lot.isEmpty()) {
            throw new RuntimeException("No parking lot available");
        }

        vehicleRepository.save(vehicle);

        // Assuming we take the first lot for simplicity
        ParkingLot parkingLot = lot.get(0);
        ParkingStrategy strategy = getStrategy(parkingLot.getEntryGate().getStrategyType());
        ParkingSpace space = strategy.assignParkingSpot(vehicle.getVehicleType());

        if (space == null || space.isOccupied()) {
            throw new RuntimeException("No parking space available");
        }

        space.setOccupied(true);
        space.setParkedVehicle(vehicle);
        spaceRepository.save(space);

        ParkingTicket ticket = new ParkingTicket();
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setVehicleRegistration(vehicle.getVehicleRegistration());
        ticket.setParkingSpaceId(space.getParkingSpaceId());

        return (ParkingTicket) ticketRepository.save(ticket);
    }

    private ParkingStrategy getStrategy(EntryGate.ParkingStrategyType type) {
        return strategies.stream()
                .filter(s -> s.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported strategy"));
    }
}
