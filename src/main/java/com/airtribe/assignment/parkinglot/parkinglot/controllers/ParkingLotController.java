package com.airtribe.assignment.parkinglot.parkinglot.controllers;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingLot;
import com.airtribe.assignment.parkinglot.parkinglot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkinglot")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<ParkingLot> createLot(@RequestBody ParkingLot lot) {
        return ResponseEntity.ok(parkingLotService.createParkingLot(lot));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getLot(@PathVariable Long id) throws Throwable {
        return ResponseEntity.ok(parkingLotService.getParkingLotById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParkingLot>> getAllLots() {
        return ResponseEntity.ok(parkingLotService.getAllLots());
    }
}
