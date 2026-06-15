package com.example.bookmyshow.search.service;

import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.search.repository.SearchRepository;
import com.example.bookmyshow.show.schema.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final SearchRepository searchRepository;


    public List<Movie> searchMoviesByTitle(String title){
        return searchRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> searchMoviesByLanguage(String language){
        return searchRepository.findByLanguageIgnoreCase(language);
    }

    public List<Movie> searchMoviesByGenre(String genre){
        return searchRepository.findByGenreIgnoreCase(genre);
    }

    public List<Show> searchShowsByMovie(Long movieId){
        return searchRepository.findByMovieMovieId(movieId);
    }
    public List<Show> searchShowsByTheatre(Long theatreId){
        return searchRepository.findByScreenTheatreTheatreId(theatreId);
    }

    public List<Show> searchShowsByCity(String city){
        return searchRepository.findByScreenTheatreCityIgnoreCase(city);

    }
}
