package com.example.bookmyshow.booking.schema;

import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bookings")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "booking_show_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "show_seat_id")
    )
    private List<ShowSeat> showSeats;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookedStatus booked;


}
