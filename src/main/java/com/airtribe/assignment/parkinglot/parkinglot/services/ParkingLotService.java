package com.airtribe.assignment.parkinglot.parkinglot.services;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingLot;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot createParkingLot(ParkingLot lot) {
        return (ParkingLot) parkingLotRepository.save(lot);
    }

    public ParkingLot getParkingLotById(Long id) throws Throwable {
        return (ParkingLot) parkingLotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking lot not found with id: " + id));
    }

    public List<ParkingLot> getAllLots() {
        return parkingLotRepository.findAll();
    }
}
