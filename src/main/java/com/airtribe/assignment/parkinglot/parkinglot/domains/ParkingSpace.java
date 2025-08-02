package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.*;

/**
 * Represents an individual parking space.
 * Mapped to a vehicle if occupied.
 */
@Entity
@SequenceGenerator(name = "parking_space_seq", sequenceName = "parking_space_seq", allocationSize = 1)
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_space_seq")
    private Long parkingSpaceId;

    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;

    private boolean isOccupied;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle parkedVehicle;

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public SpaceType getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}