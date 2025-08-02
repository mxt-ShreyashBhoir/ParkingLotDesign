package com.airtribe.assignment.parkinglot.parkinglot.domains;


import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a floor in the parking lot.
 * Holds a list of parking spaces.
 */
@Entity
@SequenceGenerator(name = "floor_seq", sequenceName = "floor_seq", allocationSize = 1)
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "floor_seq")
    private Long floorId;

    private boolean underMaintenance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParkingSpace> parkingSpaces;

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
}
