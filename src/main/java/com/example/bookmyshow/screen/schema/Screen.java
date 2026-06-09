package com.example.bookmyshow.screen.schema;

import com.example.bookmyshow.theatre.schema.Theatre;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;

    @NonNull
    private String screenName;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
