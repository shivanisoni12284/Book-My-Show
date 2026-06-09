package com.example.bookmyshow.booking.dto;

import com.example.bookmyshow.booking.schema.BookedStatus;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    private Show show;

    private List<ShowSeat> showSeats;

    private Double totalPrice;

    private BookedStatus booked;
}
