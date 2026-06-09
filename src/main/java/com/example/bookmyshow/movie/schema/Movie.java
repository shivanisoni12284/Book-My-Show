package com.example.bookmyshow.movie.schema;


import com.example.bookmyshow.show.schema.Show;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String movieName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_genre")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MovieGenre> genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_language")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MovieLanguage> language;

    @Enumerated(EnumType.STRING)
    private MovieFormat format;

    @Min(0)
    @Max(10)
    private Double rating;

    private LocalDate releaseDate;

    private String durationinminutes;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private List<String> likes;

//    @OneToMany(mappedBy = "movie")
//    private Show show;



}
