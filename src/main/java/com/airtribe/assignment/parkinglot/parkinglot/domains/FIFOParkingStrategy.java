package com.airtribe.assignment.parkinglot.parkinglot.domains;

import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.airtribe.assignment.parkinglot.parkinglot.domains.VehicleType.*;

/**
 * FIFO strategy - assigns the first available parking spot.
 */
@Component
public class FIFOParkingStrategy implements ParkingStrategy {

    private final ParkingSpaceRepository spaceRepository;

    @Autowired
    public FIFOParkingStrategy(ParkingSpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @Override
    public ParkingSpace assignParkingSpot(VehicleType vehicleType) {
        SpaceType spaceType = mapToSpaceType(vehicleType);
        List<ParkingSpace> available = spaceRepository.findByIsOccupiedFalseAndSpaceType(spaceType);
        return available.isEmpty() ? null : available.get(0);
    }

    @Override
    public boolean supports(EntryGate.ParkingStrategyType type) {
        return type == EntryGate.ParkingStrategyType.FIFO;
    }

    private SpaceType mapToSpaceType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case MOTORCYCLE -> SpaceType.MOTORCYCLE;
            case CAR -> SpaceType.CAR;
            case BUS ->  SpaceType.BUS;
            default -> throw new IllegalStateException("Unexpected value: " + vehicleType);
        };
    }
}
