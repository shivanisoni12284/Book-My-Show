package com.example.bookmyshow.MovieListing.dto;

import com.example.bookmyshow.MovieListing.schema.MovieFormat;
import com.example.bookmyshow.MovieListing.schema.MovieGenre;
import com.example.bookmyshow.MovieListing.schema.MovieLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieRequest {

    private String movieName;

    private String description;

    private List<MovieGenre> genre;

    private List<MovieLanguage> language;

    private MovieFormat format;

    private String duration;

    private Double rating;

    private LocalDate releaseDate;

}
