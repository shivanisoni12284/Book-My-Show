package com.example.bookmyshow.review.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private Long reviewId;

    private Long movieId;

    @NotNull
    @Min(1)
    @Max(10)
    private String rating;

    private String reviewText;

}
