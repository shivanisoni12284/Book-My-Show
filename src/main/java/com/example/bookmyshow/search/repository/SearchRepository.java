package com.example.bookmyshow.search.repository;

import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.search.schema.Search;
import com.example.bookmyshow.show.schema.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search ,Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByLanguageIgnoreCase(String language);

    List<Movie> findByGenreIgnoreCase(String genre);

    List<Show> findByMovieMovieId(Long movieId);

    List<Show> findByScreenTheatreTheatreId(Long theatreId);

    List<Show> findByScreenTheatreCityIgnoreCase(String city);

}
