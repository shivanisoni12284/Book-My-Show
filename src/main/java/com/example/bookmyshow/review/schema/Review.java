package com.example.bookmyshow.review.schema;


import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.userlogin.schema.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @Min(1)
    @Max(10)
    private Integer rating;

    @NotBlank
    private String reviewText;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
