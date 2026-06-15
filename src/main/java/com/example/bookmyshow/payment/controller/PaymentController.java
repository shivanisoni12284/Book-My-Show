package com.example.bookmyshow.payment.controller;


import com.example.bookmyshow.payment.dto.PaymentRequestDto;
import com.example.bookmyshow.payment.schema.Payment;
import com.example.bookmyshow.payment.service.PaymentService;
import com.example.bookmyshow.ticket.dto.TicketResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bookmyshow/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/makepayment")
    public ResponseEntity<Payment> makePayment(PaymentRequestDto paymentRequestDto){
        Payment payment = paymentService.makePayment(paymentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

//    @PostMapping("/booking/{bookingId}")
//    public ResponseEntity<TicketResponseDto> generateTicket(@PathVariable Long bookingId){
//        TicketResponseDto responseDto = ticketService.generateTicket(bookingId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
//    }

}
