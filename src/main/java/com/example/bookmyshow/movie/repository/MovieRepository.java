package com.example.bookmyshow.movie.repository;


import com.example.bookmyshow.movie.schema.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByGenre(String genre);

    List<Movie> findBylanguage(String language);

    List<Movie> findByFormat(String format);

    List<Movie> findByRating(String rating);


}
