package com.example.bookmyshow.MovieListing.controller;


import com.example.bookmyshow.MovieListing.dto.MovieRequest;
import com.example.bookmyshow.MovieListing.schema.Movie;
import com.example.bookmyshow.MovieListing.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    //create new movie
    @PostMapping("/admin/addMovie")
    public ResponseEntity<Movie> addMovie(MovieRequest movieRequest){
        Movie createdMovie = movieService.addMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    //update existing movie
    @PutMapping("/admin/updateMovie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieRequest movieRequest,@PathVariable Long id){
        Movie updatedMovie = movieService.updateMovie(movieRequest,id);
        return ResponseEntity.ok(updatedMovie);
    }

    //get movie by id
    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieWithId(id));
    }

    //get movie by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviebyGenre(@PathVariable String genre){
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));

    }

    //get movie by language
    @GetMapping("/language/{language}")
    public  ResponseEntity<List<Movie>> getMovieByLanguage(@PathVariable String language){
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }

    //get movie by format -> 2d or 3d
    @GetMapping("/format/{format}")
    public  ResponseEntity<List<Movie>> getMovieByFormat(@PathVariable String format){
        return ResponseEntity.ok(movieService.getMoviesByFormat(format));
    }

    //get all movies
    @GetMapping("/all-movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }





}
