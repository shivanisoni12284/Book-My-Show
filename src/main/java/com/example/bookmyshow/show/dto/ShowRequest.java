package com.example.bookmyshow.show.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowRequest {

    private Long screenId;

    private LocalTime showTime;

    private LocalDate showDate;

    private Long movieId;
}
