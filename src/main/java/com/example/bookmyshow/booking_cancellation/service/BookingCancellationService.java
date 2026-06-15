package com.example.bookmyshow.booking_cancellation.service;

import com.example.bookmyshow.booking.repository.BookingRepository;
import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.booking.schema.BookingStatus;
import com.example.bookmyshow.booking.service.BookingService;
import com.example.bookmyshow.booking_cancellation.repository.BookingCancellationRepository;
import com.example.bookmyshow.notification.service.NotificationService;
import com.example.bookmyshow.refund.schema.Refund;
import com.example.bookmyshow.refund.service.RefundService;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import com.example.bookmyshow.ticket.schema.Ticket;
import com.example.bookmyshow.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingCancellationService {

    private final BookingCancellationRepository bookingCancellationRepository;
    private final BookingService bookingService;
    private final TicketService ticketService;
    private final RefundService refundService;
    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;

    public String cancelBooking(Long bookingId){

        Booking booking = bookingService.getBookingById(bookingId);
        //validate booking
        bookingService.getBookingById(booking.getId());

        //validate booking status
        if(!booking.getBookingStatus().equals(BookingStatus.CONFIRMED)){
            throw new RuntimeException("booking is never confirmed or done");
        }

        Ticket ticket = booking.getTicket();

        //validate ticket
        ticketService.getTicketById(ticket.getTicketId());

        //cancel payment
        booking.setBookingStatus(BookingStatus.CANCELLED);
        for(ShowSeat showSeat : booking.getShowSeats()){
            showSeat.setBooked(false);
        }


        Refund refund = refundService.initiateRefund(bookingId);

        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        return "Booking cancelled successfully. Refund initiated";

    }

}
