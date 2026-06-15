package com.example.bookmyshow.refund.service;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.booking.service.BookingService;
import com.example.bookmyshow.notification.service.NotificationService;
import com.example.bookmyshow.payment.schema.Payment;
import com.example.bookmyshow.payment.service.PaymentService;
import com.example.bookmyshow.refund.repository.RefundRepository;
import com.example.bookmyshow.refund.schema.Refund;
import com.example.bookmyshow.refund.schema.RefundStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefundService {

    private final RefundRepository refundRepository;
    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final NotificationService notificationService;

    public Refund initiateRefund(Long bookingId){

        Booking booking = bookingService.getBookingById(bookingId);
        Payment payment = paymentService.getPaymentByBookingId(booking.getId());

        Refund refund = Refund.builder()
                .booking(booking)
                .payment(payment)
                .refundAmount(payment.getAmount())
                .refundStatus(RefundStatus.PENDING)
                .build();

        Refund savedRefund =  refundRepository.save(refund);

        notificationService.sendRefundInitiated(refund);

        return refund;

    }

    public Refund getRefundById(Long refundId){
        return refundRepository.findById(refundId).orElseThrow(() -> new RuntimeException("refund not found"));

    }
}
