package com.example.bookmyshow.payment.repository;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.payment.PaymentStatus;
import com.example.bookmyshow.payment.schema.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.bookmyshow.payment.PaymentStatus.SUCCESS;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    boolean existsByBookingAndPaymentStatus(Booking booking, PaymentStatus paymentStatus);

    Optional<Payment> findByBookingId(Long bookingId);


    BigDecimal countTotalRevenue();
}
