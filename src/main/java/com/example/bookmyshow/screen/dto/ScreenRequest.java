package com.example.bookmyshow.screen.dto;


import com.example.bookmyshow.theatre.schema.Theatre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ScreenRequest {

    private String screenName;

    private Long theatreId;
}
