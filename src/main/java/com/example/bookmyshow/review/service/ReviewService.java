package com.example.bookmyshow.review.service;

import com.example.bookmyshow.movie.repository.MovieRepository;
import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.review.dtos.MovieRatingsStats;
import com.example.bookmyshow.review.dtos.ReviewRequest;
import com.example.bookmyshow.review.dtos.ReviewResponse;
import com.example.bookmyshow.execption.DuplicateReviewException;
import com.example.bookmyshow.execption.MovieNotFoundException;
import com.example.bookmyshow.execption.ReviewNotFoundException;
import com.example.bookmyshow.execption.UnauthorizedReviewException;
import com.example.bookmyshow.review.helper.Helper;
import com.example.bookmyshow.review.mapper.ReviewMapper;
import com.example.bookmyshow.review.repository.ReviewRepository;
import com.example.bookmyshow.review.schema.Review;
import com.example.bookmyshow.userlogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReviewService {               //ApiResponse

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final Helper helper;
    private final ReviewMapper reviewMapper;


    // add Review
    public ReviewResponse addReview(ReviewRequest reviewRequest){

        //fetch movie by id
        Movie movie = movieRepository.findById(reviewRequest.getMovieId()).orElseThrow(() -> new MovieNotFoundException("movie not found"));

        //fetch user by id
        User user = helper.getCurrentUser();

        Optional<Review> existingReview = reviewRepository.findByUserAndMovie(user,movie);

        if(existingReview.isPresent()){
            throw new DuplicateReviewException("user already reviewed the movie");
        }

        Review addReview = Review.builder()
                .movie(movie)
                .user(user)
                .reviewText(reviewRequest.getReviewText())
                .rating(Integer.valueOf(reviewRequest.getRating()))
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(addReview);

        //return ResponseDto without mapstruct
//        return ReviewResponse.builder()
//                .id(addReview.getId())
//                .movieName(movie.getMovieName())
//                .userName(user.getName())
//                .reviewText(addReview.getReviewText())
//                .rating(addReview.getRating())
//                .createdAt(addReview.getCreatedAt())
//                .updatedAt(addReview.getUpdatedAt())
//                .build();

        return reviewMapper.toDto(addReview);

    }

    //get all review by movieId  -> Pgination not full fetching reviews from db but limited reviews
    public Page<Review> getAllReviewsByMovieId(Long movieId, Pageable pageable) {

        movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        return reviewRepository.findByMovieId(movieId,pageable);
    }

    //delete review
    public void deleteReview(Long reviewId){

        //fetch review
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("review with id not found"));

        //fetch user by id
        User user = helper.getCurrentUser();


        if(!review.getUser().getId().equals(user.getId())){
            throw new UnauthorizedReviewException("Please yaar apna review delete karo");
        }
        reviewRepository.deleteById(reviewId);
    }

    //update review
    public ReviewResponse updateReview(ReviewRequest reviewRequest) {
        Integer rating = Integer.valueOf(reviewRequest.getRating());

        //fetch review by id
        Review review = reviewRepository.findById(reviewRequest.getReviewId()).orElseThrow(() -> new ReviewNotFoundException("review with id not found"));

        //Authenticate User
        User user = helper.getCurrentUser();

        //only owner can update
        if(!review.getUser().getId().equals(user.getId())){  //  review se user id aur authenticated user ka id match karna chahiye
            throw new UnauthorizedReviewException("you can update only you reviews");
        }

        // partial updation
        if(rating>=1 && rating <=10){
            review.setRating(Integer.valueOf(reviewRequest.getRating()));
        }

        if(reviewRequest.getReviewText()!=null){
            review.setReviewText(reviewRequest.getReviewText());
        }

        review.setUpdatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        //return ResponseDto  using mapstruct
        return reviewMapper.toDto(review);
    }

    public MovieRatingsStats getMovieRatingStat(Long movieId) {
        movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie Not found"));

        Double avgRating = reviewRepository.getAverageRatingByMovieId(movieId);

        Long countReviews = reviewRepository.countReviewsByMovieId(movieId);

        return new MovieRatingsStats(avgRating == null ? 0.0 : avgRating,countReviews);
    }

}
