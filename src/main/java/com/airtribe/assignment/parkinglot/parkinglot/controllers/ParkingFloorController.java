package com.airtribe.assignment.parkinglot.parkinglot.controllers;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingFloor;
import com.airtribe.assignment.parkinglot.parkinglot.services.ParkingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/floors")
public class ParkingFloorController {

    @Autowired
    private ParkingFloorService parkingFloorService;

    @PostMapping
    public ResponseEntity<ParkingFloor> createFloor(@RequestBody ParkingFloor floor) {
        return ResponseEntity.ok(parkingFloorService.createFloor(floor));
    }

    @PatchMapping("/{id}/maintenance")
    public ResponseEntity<String> toggleMaintenance(@PathVariable Long id, @RequestParam boolean underMaintenance) {
        parkingFloorService.setMaintenance(id, underMaintenance);
        return ResponseEntity.ok("Maintenance mode updated");
    }
}
