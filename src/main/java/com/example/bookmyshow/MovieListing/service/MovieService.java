package com.example.bookmyshow.MovieListing.service;

import com.example.bookmyshow.MovieListing.dto.MovieRequest;
import com.example.bookmyshow.MovieListing.repository.MovieRepository;
import com.example.bookmyshow.MovieListing.schema.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    //add movie
    public Movie addMovie(MovieRequest movieRequest){

       Movie createdMovie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .description(movieRequest.getDescription())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .format(movieRequest.getFormat())
                .duration(movieRequest.getDuration())
                .rating(movieRequest.getRating())
                .releaseDate(movieRequest.getReleaseDate())
                .createdAt(LocalDateTime.now())
                .build();

       return movieRepository.save(createdMovie);

    }

    // update movie
    public Movie updateMovie(MovieRequest movieRequest,Long id){

        if(!movieRepository.existsById(id)){
            throw new RuntimeException("movie with id doesn't exist");
        }
        Movie updatedMovie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .description(movieRequest.getDescription())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .format(movieRequest.getFormat())
                .rating(movieRequest.getRating())
                .releaseDate(movieRequest.getReleaseDate())
                .updatedAt(LocalDateTime.now())
                .build();

        return movieRepository.save(updatedMovie);
    }


    // get all movies
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // get movie with id
    public Movie getMovieWithId(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid id"));
    }

    // get movies by genre
    public List<Movie> getMoviesByGenre(String genre){
        return movieRepository.getMoviesByGenre(genre);

    }

    // get movies by language
    public List<Movie> getMoviesByLanguage(String language){
        return movieRepository.getMovieBylanguage(language);
    }

    // get movies by format
    public List<Movie> getMoviesByFormat(String format){
        return movieRepository.getMovieByFormat(format);
    }

    // get movies by rating
    public List<Movie> getMoviesByRating(String rating){
        return movieRepository.getMovieByFormat(rating);
    }


}
