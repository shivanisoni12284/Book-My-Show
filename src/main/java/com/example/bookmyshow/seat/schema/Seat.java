package com.example.bookmyshow.seat.schema;

import com.example.bookmyshow.screen.schema.Screen;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long seatId;

   @Enumerated(EnumType.STRING)
   private SeatType seatType;

   private String seatNumber;

   private boolean booked;

   @ManyToOne
   @JoinColumn(name = "screen_id")
   private Screen screen;
}
