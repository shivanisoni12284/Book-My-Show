package com.example.bookmyshow.booking.controller;


import com.example.bookmyshow.booking.dto.BookingRequestDto;
import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.booking.service.BookingService;
import com.example.bookmyshow.theatre.dto.TheatreRequest;
import com.example.bookmyshow.theatre.schema.Theatre;
import com.example.bookmyshow.theatre.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bookmyshow/theatre")
@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createTheatre(@RequestBody BookingRequestDto bookingRequestDto){
        Booking createdBooking = bookingService.createBooking(bookingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    // find booking by id
    @GetMapping("/single-booking/{bookingId}")
    public ResponseEntity<Booking>  getTheatreById(@PathVariable("theaterId") Long theatreId){
        Booking booking=bookingService.getBookingById(theatreId);
        return ResponseEntity.ok(booking);
    }


    // find all bookings
    @GetMapping("/all-bookings")
    public ResponseEntity<List<Booking>> findAllTheatre(){
        List<Booking> theatres = bookingService.getAllBookings();
        return ResponseEntity.ok(theatres);
    }

    // Delete booking by id

    @DeleteMapping("{bookingId}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long theatreId){
        bookingService.deleteBooking(theatreId);
        return ResponseEntity.ok("theatre deleted successfully");
    }
}
