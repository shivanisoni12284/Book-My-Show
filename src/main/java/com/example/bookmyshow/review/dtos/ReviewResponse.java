package com.example.bookmyshow.review.dtos;


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
