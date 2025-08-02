package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main entry point for parking lot system.
 * Contains all floors, and entry/exit configurations.
 */
@Entity
@SequenceGenerator(name = "lot_seq", sequenceName = "lot_seq", allocationSize = 1)
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lot_seq")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParkingFloor> floors;

    @OneToOne(cascade = CascadeType.ALL)
    private EntryGate entryGate;

    @OneToOne(cascade = CascadeType.ALL)
    private ExitGate exitGate;

    @Transient
    private Map<String, ParkingTicket> activeTickets = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public EntryGate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(EntryGate entryGate) {
        this.entryGate = entryGate;
    }

    public ExitGate getExitGate() {
        return exitGate;
    }

    public void setExitGate(ExitGate exitGate) {
        this.exitGate = exitGate;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }

    public void setActiveTickets(Map<String, ParkingTicket> activeTickets) {
        this.activeTickets = activeTickets;
    }
}
