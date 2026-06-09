package com.example.bookmyshow.seat.dto;

import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.seat.schema.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {

    private Long seatId;

    private SeatType seatType;

    private String seatNumber;

    private boolean booked;

    private Long screenId;


}
