package com.example.bookmyshow.Review.controller;


import com.example.bookmyshow.Review.enums.ReactionType;
import com.example.bookmyshow.Review.repository.ReactionRepository;
import com.example.bookmyshow.Review.schema.ReactionReview;
import com.example.bookmyshow.Review.service.ReviewReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReactionReviewController {

    private final ReviewReactionService reviewReactionService;

    @PostMapping("/reaction/{reviewId}")
    public ResponseEntity<ReactionReview> react(@PathVariable Long reviewId, @RequestParam ReactionType reactionType){
        ReactionReview reaction = reviewReactionService.react(reviewId,reactionType);
        return ResponseEntity.ok(reaction);
    }
}
