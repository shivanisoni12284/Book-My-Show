package com.example.bookmyshow.theatre.schema;


import com.example.bookmyshow.screen.schema.Screen;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "theatre")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String theatreName;

    private String address;

    private String city;

    private String state;

    private Long pincode;

    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;

    private Long contact;

    private String email;
}
