package com.example.bookmyshow.theatre.dto;

import com.example.bookmyshow.screen.schema.Screen;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreRequest {

    private String theatreName;

    private String address;

    private String city;

    private String state;

    private Long pincode;

    private Long contact;

    private String email;

}
