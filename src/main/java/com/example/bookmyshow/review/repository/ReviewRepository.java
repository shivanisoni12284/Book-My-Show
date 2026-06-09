package com.example.bookmyshow.review.repository;

import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.review.schema.Review;
import com.example.bookmyshow.userlogin.schema.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    Optional<Review> findByUserAndMovie(User user, Movie movie);

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
