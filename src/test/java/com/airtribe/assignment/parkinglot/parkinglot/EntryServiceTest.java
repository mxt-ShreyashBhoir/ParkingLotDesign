package com.airtribe.assignment.parkinglot.parkinglot;

import com.airtribe.assignment.parkinglot.parkinglot.domains.*;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingLotRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingTicketRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.VehicleRepository;
import com.airtribe.assignment.parkinglot.parkinglot.services.EntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntryServiceTest {

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @Mock
    private ParkingTicketRepository ticketRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ParkingSpaceRepository spaceRepository;

    @Mock
    private ParkingStrategy parkingStrategy;

    private EntryService entryService;

    private Vehicle vehicle;
    private ParkingLot parkingLot;
    private ParkingSpace parkingSpace;
    private EntryGate entryGate;

    @BeforeEach
    void setUp() {
        entryGate = new EntryGate();
        entryGate.setStrategyType(EntryGate.ParkingStrategyType.RANDOM);

        parkingLot = new ParkingLot();
        parkingLot.setId(1L);
        parkingLot.setEntryGate(entryGate);

        vehicle = new Vehicle();
        vehicle.setVehicleRegistration("MH01AB1234");
        vehicle.setVehicleType(VehicleType.CAR);

        parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(100L);
        parkingSpace.setOccupied(false);

        when(parkingStrategy.supports(EntryGate.ParkingStrategyType.RANDOM)).thenReturn(true);

        entryService = new EntryService(
                parkingLotRepository,
                ticketRepository,
                vehicleRepository,
                spaceRepository,
                List.of(parkingStrategy)
        );
    }

    @Test
    void testProcessEntry_Success() {
        // Arrange
        when(parkingLotRepository.findAll()).thenReturn(List.of(parkingLot));
        when(parkingStrategy.assignParkingSpot(VehicleType.CAR)).thenReturn(parkingSpace);
        when(vehicleRepository.save(ArgumentMatchers.<Vehicle>any())).thenReturn(vehicle);
        when(spaceRepository.save(ArgumentMatchers.<ParkingSpace>any())).thenReturn(parkingSpace);
        when(ticketRepository.save(ArgumentMatchers.<ParkingTicket>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ParkingTicket ticket = entryService.processEntry(vehicle);

        // Assert
        assertNotNull(ticket);
        assertEquals("MH01AB1234", ticket.getVehicleRegistration());
        assertEquals(100L, ticket.getParkingSpaceId());
        verify(vehicleRepository).save(vehicle);
        verify(spaceRepository).save(parkingSpace);
        verify(ticketRepository).save(ArgumentMatchers.<ParkingTicket>any());

    }



    @Test
    void testProcessEntry_NoSpaceAvailable_ThrowsException() {
        when(parkingLotRepository.findAll()).thenReturn(List.of(parkingLot));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            entryService.processEntry(vehicle);
        });

        assertEquals("No parking space available", exception.getMessage());
    }

    @Test
    void testProcessEntry_SpaceOccupied_ThrowsException() {
        parkingSpace.setOccupied(true);

        when(parkingLotRepository.findAll()).thenReturn(List.of(parkingLot));
        when(parkingStrategy.assignParkingSpot(VehicleType.CAR)).thenReturn(parkingSpace);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            entryService.processEntry(vehicle);
        });

        assertEquals("No parking space available", exception.getMessage());
    }
}
