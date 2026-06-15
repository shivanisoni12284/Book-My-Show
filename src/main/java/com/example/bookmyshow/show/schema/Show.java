package com.example.bookmyshow.show.schema;


import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.theatre.schema.Theatre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Table(name = "shows")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    private LocalTime showTime;

    private LocalDate showDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "show_screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
