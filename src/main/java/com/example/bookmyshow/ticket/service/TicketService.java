package com.example.bookmyshow.ticket.service;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.booking.schema.BookingStatus;
import com.example.bookmyshow.booking.service.BookingService;
import com.example.bookmyshow.ticket.dto.TicketResponseDto;
import com.example.bookmyshow.ticket.repository.TicketRepository;
import com.example.bookmyshow.ticket.schema.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingService bookingService;

    public TicketResponseDto generateTicket(Booking booking){

        if(ticketRepository.existsByBooking(booking)){
            throw new RuntimeException("Ticket already generated");
        }

       if(!booking.getBookingStatus().equals(BookingStatus.CONFIRMED)){
           throw new RuntimeException("Booking is not confirmed");

       }

       Ticket ticket = Ticket.builder()
               .ticketNumber("TKT-"+UUID.randomUUID().toString().substring(0,8).toUpperCase())
               .booking(booking)
               .generatedAt(LocalDateTime.now())
               .build();

       Ticket savedTicket = ticketRepository.save(ticket);
       booking.setTicket(savedTicket);

       return convertToDto(savedTicket,booking);
    }

    public Ticket getTicketById(Long ticketId){
        return ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("ticket not found"));

    }

    public Ticket getTicketByBooking(Long bookingId){
        Booking booking = bookingService.getBookingById(bookingId);
        return  ticketRepository.findByBooking(booking).orElseThrow(() -> new RuntimeException("Ticket Not Found"));

    }

    private TicketResponseDto convertToDto(Ticket ticket, Booking booking) {

        return TicketResponseDto.builder()
                .ticketNumber(ticket.getTicketNumber())
                .movieName(booking.getShow().getMovie().getMovieName())
                .theatreName(booking.getShow().getTheatre().getTheatreName())
                .amount(booking.getTotalPrice())
                .generatedAt(ticket.getGeneratedAt())
                .seatNumbers(booking.getShowSeats().stream().map(showSeat -> showSeat.getSeat().getSeatNumber()).toList())
                .showTime(booking.getShow().getShowTime())
                .build();

    }

}
