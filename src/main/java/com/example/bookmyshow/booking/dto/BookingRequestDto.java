package com.example.bookmyshow.booking.dto;

import com.example.bookmyshow.booking.schema.BookingStatus;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    private Show show;

    private List<ShowSeat> showSeats;

    private BigDecimal totalPrice;

    private BookingStatus booked;
}
