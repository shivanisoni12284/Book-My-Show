package com.example.bookmyshow.refund.schema;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.payment.schema.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    @OneToOne
    private Booking booking;

    @OneToOne
    private Payment payment;

    private BigDecimal refundAmount;

    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus;
}
