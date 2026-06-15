package com.example.bookmyshow.payment.dto;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.payment.PaymentStatus;
import com.example.bookmyshow.userlogin.dto.LoginRequest;
import com.example.bookmyshow.userlogin.dto.UserRequest;
import com.example.bookmyshow.userlogin.schema.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private Long userId;

    private Long bookingId;

}
