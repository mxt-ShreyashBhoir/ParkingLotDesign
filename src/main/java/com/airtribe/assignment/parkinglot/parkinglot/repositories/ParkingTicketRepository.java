package com.airtribe.assignment.parkinglot.parkinglot.repositories;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository  extends JpaRepository<ParkingTicket, Long> {
}
