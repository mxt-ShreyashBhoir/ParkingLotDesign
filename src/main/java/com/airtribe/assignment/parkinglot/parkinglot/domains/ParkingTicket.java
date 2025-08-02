package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Parking ticket generated upon vehicle entry.
 * Stores basic entry information.
 */
@Entity
@SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize = 1)
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    private Long ticketId;

    private LocalDateTime entryTime;

    private String vehicleRegistration;

    private Long parkingSpaceId;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }
}

