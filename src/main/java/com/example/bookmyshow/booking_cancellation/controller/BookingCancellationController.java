package com.example.bookmyshow.booking_cancellation.controller;


import com.example.bookmyshow.booking_cancellation.service.BookingCancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancelbooking")
@RequiredArgsConstructor
public class BookingCancellationController {

    private final BookingCancellationService bookingCancellationService;

    public String cancelBooking(@PathVariable Long bookingId){
        return bookingCancellationService.cancelBooking(bookingId);
    }
}
