package com.airtribe.assignment.parkinglot.parkinglot.repositories;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
