package com.example.bookmyshow.ticket.controller;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.ticket.dto.TicketResponseDto;
import com.example.bookmyshow.ticket.repository.TicketRepository;
import com.example.bookmyshow.ticket.schema.Ticket;
import com.example.bookmyshow.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId){
        Ticket ticket= ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Ticket> getTicketByBooking(@PathVariable Long bookingId){
        Ticket ticket= ticketService.getTicketByBooking(bookingId);
        return ResponseEntity.ok(ticket);
    }
}
