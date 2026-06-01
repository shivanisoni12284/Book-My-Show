package com.example.bookmyshow.Review.dtos;


import com.example.bookmyshow.MovieListing.schema.Movie;
import com.example.bookmyshow.UserLogin.schema.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {

    private Long id;

    private String movieName;

    private String userName;

    private Integer rating;

    private String reviewText;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
