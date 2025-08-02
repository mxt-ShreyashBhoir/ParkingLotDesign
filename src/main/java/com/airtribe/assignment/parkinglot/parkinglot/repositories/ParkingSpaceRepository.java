package com.airtribe.assignment.parkinglot.parkinglot.repositories;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingSpace;
import com.airtribe.assignment.parkinglot.parkinglot.domains.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository  extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findByIsOccupiedFalseAndSpaceType(SpaceType spaceType);
}
