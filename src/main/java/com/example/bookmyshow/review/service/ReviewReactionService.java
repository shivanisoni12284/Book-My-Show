package com.example.bookmyshow.review.service;

import com.example.bookmyshow.review.enums.ReactionType;
import com.example.bookmyshow.execption.ReviewNotFoundException;
import com.example.bookmyshow.review.helper.Helper;
import com.example.bookmyshow.review.repository.ReactionRepository;
import com.example.bookmyshow.review.repository.ReviewRepository;
import com.example.bookmyshow.review.schema.ReactionReview;
import com.example.bookmyshow.review.schema.Review;
import com.example.bookmyshow.userlogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewReactionService {

    private final ReviewRepository reviewRepository;
    private final Helper helper;
    private final ReactionRepository reactionRepository;

    public ReactionReview react(Long reviewId, ReactionType reactionType) {

          User user = helper.getCurrentUser();

          Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("review not found"));

          ReactionReview existing = reactionRepository.findByUserAndReview(user, review);

          if(existing != null){
              existing.setReactionType(reactionType);
              return reactionRepository.save(existing);
          }
          ReactionReview reactionReview  = ReactionReview.builder()
                  .user(user)
                  .review(review)
                  .reactionType(reactionType)
                  .build();

          return reactionRepository.save(reactionReview);


    }
}
