package com.airtribe.assignment.parkinglot.parkinglot.services;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingFloor;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingFloorService {
    private final ParkingFloorRepository parkingFloorRepository;

    @Autowired
    public ParkingFloorService(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }


    public ParkingFloor createFloor(ParkingFloor floor) {
        return parkingFloorRepository.save(floor);
    }

    public void setMaintenance(Long id, boolean underMaintenance) {
        ParkingFloor floor = parkingFloorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Parking floor not found with id: " + id));
        floor.setUnderMaintenance(underMaintenance);
        parkingFloorRepository.save(floor);
    }
}
