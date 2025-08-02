package com.airtribe.assignment.parkinglot.parkinglot;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingSpace;
import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingTicket;
import com.airtribe.assignment.parkinglot.parkinglot.domains.PaymentStrategy;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingSpaceRepository;
import com.airtribe.assignment.parkinglot.parkinglot.repositories.ParkingTicketRepository;
import com.airtribe.assignment.parkinglot.parkinglot.services.ExitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExitServiceTest {

    @Mock
    private ParkingTicketRepository ticketRepository;

    @Mock
    private ParkingSpaceRepository spaceRepository;

    @Mock
    private PaymentStrategy paymentStrategy;

    @InjectMocks
    private ExitService exitService;

    private ParkingTicket ticket;
    private ParkingSpace space;

    @BeforeEach
    void setUp() {

        ticket = new ParkingTicket();
        ticket.setTicketId(1L);
        ticket.setParkingSpaceId(101L);
        ticket.setEntryTime(LocalDateTime.now().minusHours(2));
        ticket.setVehicleRegistration("MH01AB1234");

        space = new ParkingSpace();
        space.setParkingSpaceId(101L);
        space.setOccupied(true);
    }

    @Test
    void testProcessExit_Success() throws Throwable {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(spaceRepository.findById(101L)).thenReturn(Optional.of(space));
        when(paymentStrategy.pay(anyLong())).thenReturn(40.0);

        double result = exitService.processExit(1L, paymentStrategy);

        assertEquals(40.0, result);
        assertFalse(space.isOccupied());
        assertNull(space.getParkedVehicle());

        verify(ticketRepository).findById(1L);
        verify(spaceRepository).findById(101L);
        verify(spaceRepository).save(space);
        verify(ticketRepository).delete(ticket);
        verify(paymentStrategy).pay(anyLong());
    }

    @Test
    void testProcessExit_InvalidTicket_ThrowsException() {
        when(ticketRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> exitService.processExit(999L, paymentStrategy));

        assertEquals("Invalid ticket ID: 999", ex.getMessage());
        verify(ticketRepository).findById(999L);
    }

    @Test
    void testProcessExit_SpaceNotFound_ThrowsException() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(spaceRepository.findById(101L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> exitService.processExit(1L, paymentStrategy));

        assertEquals("Parking space not found", ex.getMessage());
        verify(ticketRepository).findById(1L);
        verify(spaceRepository).findById(101L);
    }
}
