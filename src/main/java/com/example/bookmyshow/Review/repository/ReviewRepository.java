package com.example.bookmyshow.Review.repository;

import com.example.bookmyshow.MovieListing.schema.Movie;
import com.example.bookmyshow.Review.schema.Review;
import com.example.bookmyshow.UserLogin.schema.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    Optional<Review> findByUserandMovie(User user, Movie movie);

    //pagination
    Page<Review> findByMovieId(Long movieId, Pageable pageable);
//    List<Review> findByMovieId(Long movieId);

    @Query(
            "SELECT AVG(r.rating) FROM Review r WHERE r.movie.id = :movieId"
    )
    Double getAverageRatingByMovieId(Long movieId);

    @Query(
        "SELECT COUNT(r.rating) From Review r WHERE r.movie.id = :movieId"
    )
    Long countReviewsByMovieId(Long movieId);
}
