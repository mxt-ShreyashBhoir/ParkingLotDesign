package com.airtribe.assignment.parkinglot.parkinglot.controllers;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingSpace;
import com.airtribe.assignment.parkinglot.parkinglot.services.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService spaceService;

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpaces(@RequestParam String type) {
        return ResponseEntity.ok(spaceService.findAvailableSpaces(type));
    }
}
