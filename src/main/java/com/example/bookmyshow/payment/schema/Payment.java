package com.example.bookmyshow.payment.schema;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.payment.PaymentStatus;
import com.example.bookmyshow.userlogin.dto.LoginRequest;
import com.example.bookmyshow.userlogin.dto.UserRequest;
import com.example.bookmyshow.userlogin.schema.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments",
        indexes = {
           @Index(name = "idx_transaction",columnList="transactionId"),
           @Index(name = "idx_booking",columnList = "booking_id")
        }

)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "booked_id")
    private Booking booking;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, unique = true)
    private String transactionId;

    private LocalDateTime paymentTime;
}
