package com.airtribe.assignment.parkinglot.parkinglot.controllers;

import com.airtribe.assignment.parkinglot.parkinglot.domains.FIFOParkingStrategy;
import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingTicket;
import com.airtribe.assignment.parkinglot.parkinglot.domains.RandomParkingStrategy;
import com.airtribe.assignment.parkinglot.parkinglot.domains.Vehicle;
import com.airtribe.assignment.parkinglot.parkinglot.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private RandomParkingStrategy randomStrategy;

    @Autowired
    private FIFOParkingStrategy fifoStrategy;

    @PostMapping
    public ParkingTicket handleEntry(@RequestBody Vehicle vehicle) {
        return entryService.processEntry(vehicle);
    }
}
