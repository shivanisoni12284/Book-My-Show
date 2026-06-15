package com.example.bookmyshow.analytics.service;

import com.example.bookmyshow.booking.repository.BookingRepository;
import com.example.bookmyshow.booking_cancellation.repository.BookingCancellationRepository;
import com.example.bookmyshow.movie.repository.MovieRepository;
import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.payment.repository.PaymentRepository;
import com.example.bookmyshow.refund.repository.RefundRepository;
import com.example.bookmyshow.theatre.repository.TheatreRepository;
import com.example.bookmyshow.theatre.schema.Theatre;
import com.example.bookmyshow.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final BookingCancellationRepository bookingCancellationRepository;
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final RefundRepository refundRepository;



    public Long getTotalBooking(){
        return bookingRepository.CountAllBookings();
    }

    public BigDecimal getTotalRevenue(){
        return paymentRepository.countTotalRevenue();
    }

    public Long getTotalTicketSold(){
        return ticketRepository.CountAllBySold();
    }

    public Movie getMostBookedMovie(){
        return movieRepository.findByMostBooked();
    }

    public Theatre getMostBookedTheatre(){
        return theatreRepository.findByMostBooked();
    }

    public Long getCancelledBookingCount(){
        return bookingCancellationRepository.CountAllCancelledBookings();
    }

    public Long getCompletedRefundCounts(){
        return refundRepository.CountAllByCompleted();
    }


}
