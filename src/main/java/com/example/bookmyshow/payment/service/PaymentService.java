package com.example.bookmyshow.payment.service;


import com.example.bookmyshow.booking.repository.BookingRepository;
import com.example.bookmyshow.booking.schema.BookingStatus;
import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.booking.service.BookingService;
import com.example.bookmyshow.payment.PaymentStatus;
import com.example.bookmyshow.payment.dto.PaymentRequestDto;
import com.example.bookmyshow.payment.repository.PaymentRepository;
import com.example.bookmyshow.payment.schema.Payment;
import com.example.bookmyshow.ticket.service.TicketService;
import com.example.bookmyshow.userlogin.schema.User;
import com.example.bookmyshow.userlogin.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final BookingService bookingService;
    private final TicketService ticketService;

    //create payment
    @Transactional
    public Payment makePayment(PaymentRequestDto paymentRequestDto){
//        authenticate user
        User user = userService.getUserById(paymentRequestDto.getUserId());    //always fetch from database

        //fetching boking from database
        Booking booking = bookingService.getBookingById(paymentRequestDto.getBookingId());

        if(!booking.getUser().getId().equals(paymentRequestDto.getUserId())){
            throw new RuntimeException("Booking does not belong to user");
        }

        if(booking.getBookingStatus() == BookingStatus.CANCELLED){
            throw new RuntimeException("cannot pay for a cancelled booking");
        }
        if(booking.getBookingStatus() == BookingStatus.CONFIRMED){
            throw new RuntimeException("Booking already confirmed");
        }

        if(paymentRepository.existsByBookingAndPaymentStatus(booking,PaymentStatus.SUCCESS)){
            throw new RuntimeException("Payment already completed");
        }

        if(paymentRequestDto.getAmount().compareTo(booking.getTotalPrice()) != 0){   // 0 -> equal , 1 -> bigger , -1 -> smaller
            throw new RuntimeException("Incorrect payment amount");
        }

        Payment payment = Payment.builder()
                .paymentStatus(PaymentStatus.PROCESSING)
                .paymentTime(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .user(user)
                .booking(booking)
                .amount(paymentRequestDto.getAmount())
                .build();

        payment = paymentRepository.save(payment); // this ensures i have genreated payment Id

        boolean paymentSuccess = processPaymentGateway();

        if(paymentSuccess){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            ticketService.generateTicket(booking);
        }
        else{
            payment.setPaymentStatus(PaymentStatus.FAILED);
            booking.setBookingStatus(BookingStatus.PAYMENT_FAILED);
        }

        paymentRepository.save(payment);
        bookingRepository.save(booking);

        return payment;
    }

    private boolean processPaymentGateway(){
        return true;
    }

    public Payment getPaymentByBookingId(Long bookingId){
        bookingService.getBookingById(bookingId);
        return paymentRepository.findByBookingId(bookingId).orElseThrow(()-> new RuntimeException("Payment not fount"));
    }
}
