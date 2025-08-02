package com.airtribe.assignment.parkinglot.parkinglot.repositories;

import com.airtribe.assignment.parkinglot.parkinglot.domains.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {
}
