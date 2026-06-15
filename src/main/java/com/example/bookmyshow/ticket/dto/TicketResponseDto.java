package com.example.bookmyshow.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private String ticketNumber;

    private String movieName;

    private String theatreName;

    private LocalTime showTime;

    private List<String> seatNumbers;

    private BigDecimal amount;

    private LocalDateTime generatedAt;

}
