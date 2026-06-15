package com.example.bookmyshow.analytics.controller;

import com.example.bookmyshow.analytics.service.AnalyticsService;
import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.theatre.schema.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/total-bookings")
    public ResponseEntity<Long> getTotalBooking(){
        Long count = analyticsService.getTotalBooking();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<BigDecimal> getTotalRevenue(){
        BigDecimal revenue = analyticsService.getTotalRevenue();
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/total-ticket-sold")
    public ResponseEntity<Long> getTotalTicketSold(){
        Long allSoldTicekt = analyticsService.getTotalTicketSold();
        return ResponseEntity.ok(allSoldTicekt);
    }

    @GetMapping("/top-movie")
    public ResponseEntity<Movie> getMostBookedMovie(){
        Movie movie =  analyticsService.getMostBookedMovie();
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/total-theatre")
    public ResponseEntity<Theatre> getMostBookedTheatre(){
        Theatre AllBookedTheatre =  analyticsService.getMostBookedTheatre();
        return ResponseEntity.ok(AllBookedTheatre);
    }

    @GetMapping("/cancelled-bookings")
    public ResponseEntity<Long> getCancelledBookingCount(){
        Long count =  analyticsService.getCancelledBookingCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/completed-refunds")
    public ResponseEntity<Long> getCompletedRefundCounts(){
        Long count = analyticsService.getCompletedRefundCounts();
        return ResponseEntity.ok(count);
    }
}
