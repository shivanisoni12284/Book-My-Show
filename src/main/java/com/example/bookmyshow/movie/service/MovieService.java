package com.example.bookmyshow.movie.service;

import com.example.bookmyshow.execption.MovieNotFoundException;
import com.example.bookmyshow.movie.dto.MovieRequest;
import com.example.bookmyshow.movie.repository.MovieRepository;
import com.example.bookmyshow.movie.schema.Movie;
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
                .durationinminutes(movieRequest.getDuration())
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

        Movie movie = getMovieWithId(id);


        movie.setMovieName(movieRequest.getMovieName());
        movie.setDescription(movieRequest.getDescription());
        movie.setGenre(movieRequest.getGenre());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setFormat(movieRequest.getFormat());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setUpdatedAt(LocalDateTime.now());
        return movieRepository.save(movie);
    }


    // get all movies
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // get movie with id
    public Movie getMovieWithId(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found with id :"+id));
    }

    // get movies by genre
    public List<Movie> findByGenre(String genre){
        return movieRepository.findByGenre(genre);

    }

    // get movies by language
    public List<Movie> findByLanguage(String language){
        return movieRepository.findBylanguage(language);
    }

    // get movies by format
    public List<Movie> findByFormat(String format){
        return movieRepository.findByFormat(format);
    }

    // get movies by rating
    public List<Movie> findByRating(String rating){
        return movieRepository.findByRating(rating);
    }

    //delete movie
    public void deleteMovie(Long movieId){
        Movie movie = getMovieWithId(movieId);
        movieRepository.delete(movie);
    }
}
