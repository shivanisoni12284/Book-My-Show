package com.example.bookmyshow.MovieListing.dto;

import com.example.bookmyshow.MovieListing.schema.MovieFormat;
import com.example.bookmyshow.MovieListing.schema.MovieGenre;
import com.example.bookmyshow.MovieListing.schema.MovieLanguage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MovieRequest {

    private String movieName;

    private String description;

    private MovieGenre genre;

    private MovieLanguage language;

    private MovieFormat format;

    private String duration;

    private Double rating;

    private LocalDateTime releaseDate;

}
