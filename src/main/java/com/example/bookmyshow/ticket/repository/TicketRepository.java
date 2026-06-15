package com.example.bookmyshow.ticket.repository;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.ticket.schema.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findByBooking(Booking booking);

    boolean existsByBooking(Booking booking);


    Long CountAllBySold();
}
