package com.example.bookmyshow.MovieListing.repository;


import com.example.bookmyshow.MovieListing.schema.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMovieBylanguage(String language);

    List<Movie> getMovieByFormat(String format);

}
