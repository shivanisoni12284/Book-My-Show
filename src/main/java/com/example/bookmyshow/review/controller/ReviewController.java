package com.example.bookmyshow.review.controller;

import com.example.bookmyshow.review.dtos.MovieRatingsStats;
import com.example.bookmyshow.review.dtos.ReviewRequest;
import com.example.bookmyshow.review.dtos.ReviewResponse;
import com.example.bookmyshow.review.schema.ReactionReview;
import com.example.bookmyshow.review.schema.Review;
import com.example.bookmyshow.review.service.ReviewReactionService;
import com.example.bookmyshow.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewReactionService reviewReactionService;

    // add new review
    @PostMapping("/add-review")
    public ResponseEntity<ReviewResponse> addReview(@Valid @RequestBody ReviewRequest reviewRequest){

        ReviewResponse response = reviewService.addReview(reviewRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // update existing review    using mapstruct
    @PutMapping("/update-review")
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody ReviewRequest reviewRequest){

        ReviewResponse updateReview = reviewService.updateReview(reviewRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    //get all reviews by movie id --> PAGINATION
    @GetMapping("/reviews-of-movie/{movieId}")
    public ResponseEntity<Page<Review>> getReviewsByMovieId(@PathVariable Long movieId,@RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "10") int size){
        Page<Review> reviewsPerMovie = reviewService.getAllReviewsByMovieId(movieId, PageRequest.of(page,size, Sort.by("createdAt").descending())); // Newest reviews first
        return ResponseEntity.ok(reviewsPerMovie);
    }

    // delete review
    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

    //avg review of movie and count number of reviews of movie
    @GetMapping("/rating-stats/{movieId}")
    public ResponseEntity<MovieRatingsStats> getMovieRatingStat(@Valid @PathVariable Long movieId){
        MovieRatingsStats avgAndCountReviews = reviewService.getMovieRatingStat(movieId);
        return ResponseEntity.ok(avgAndCountReviews);
    }

//    //Likes and DisLikes
//    @GetMapping("/like/dislike")
//    public ResponseEntity<ReactionReview> like(ReactionReview reactionReview){
//        String reaction = String.valueOf(reactionReview.getReactionType());
//        ReactionReview reviewWithReaction = reactionReview;
//        if(reaction.equals("like") ){
//            reviewWithReaction = reviewReactionService.like(reactionReview);
//        }
//        if(reaction.equals("disLike")){
//             reviewWithReaction = reviewReactionService.disLike(reactionReview);
//        }
//
//        return ResponseEntity.ok(reviewWithReaction);
//    }
}
