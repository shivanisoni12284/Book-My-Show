package com.example.bookmyshow.showseat.dto;

import com.example.bookmyshow.seat.schema.Seat;
import com.example.bookmyshow.show.schema.Show;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShowSeatRequestDto {

    private Long showId;

    private Long seatId;

    private Double price;

    private Boolean booked;
}
