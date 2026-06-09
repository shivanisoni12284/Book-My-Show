package com.example.bookmyshow.review.controller;


import com.example.bookmyshow.review.enums.ReactionType;
import com.example.bookmyshow.review.schema.ReactionReview;
import com.example.bookmyshow.review.service.ReviewReactionService;
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
