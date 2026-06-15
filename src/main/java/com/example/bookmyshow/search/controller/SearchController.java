package com.example.bookmyshow.search.controller;


import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.search.service.SearchService;
import com.example.bookmyshow.show.schema.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<List<Movie>> searchMoviesByTitle(@PathVariable String title){
        List<Movie> movies = searchService.searchMoviesByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/language/{language}")
    public ResponseEntity<List<Movie>> searchMoviesByLanguage(@PathVariable String language){
        List<Movie> movies = searchService.searchMoviesByLanguage(language);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<List<Movie>> searchMoviesByGenre(@PathVariable String genre) {
        List<Movie> movies = searchService.searchMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/shows/movie/{movieId}")
    public ResponseEntity<List<Show>> searchShowsByMovie(@PathVariable Long movieId){
        List<Show> shows = searchService.searchShowsByMovie(movieId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/shows/theatre/{theatreId}")
    public ResponseEntity<List<Show>> searchShowsByTheatre( @PathVariable Long theatreId){
        List<Show> shows = searchService.searchShowsByTheatre(theatreId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/shows/city/{city}")
    public ResponseEntity<List<Show>> searchShowsByCity( @PathVariable String city){
        List<Show> shows = searchService.searchShowsByCity(city);
        return ResponseEntity.ok(shows);
    }
}
