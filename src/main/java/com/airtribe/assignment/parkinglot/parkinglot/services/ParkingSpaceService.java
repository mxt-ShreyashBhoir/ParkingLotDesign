package com.airtribe.assignment.parkinglot.parkinglot.services;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingSpace;
import com.airtribe.assignment.parkinglot.parkinglot.domains.SpaceType;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public List<ParkingSpace> findAvailableSpaces(String type) {
        return parkingSpaceRepository.findByIsOccupiedFalseAndSpaceType(SpaceType.valueOf(type));
    }
}
