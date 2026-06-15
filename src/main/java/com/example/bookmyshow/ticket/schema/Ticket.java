package com.example.bookmyshow.ticket.schema;


import com.example.bookmyshow.booking.schema.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    private Long ticketId;

    private String ticketNumber;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private LocalDateTime generatedAt;

}
