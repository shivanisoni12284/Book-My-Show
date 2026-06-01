package com.example.bookmyshow.Review.service;

import com.example.bookmyshow.Review.enums.ReactionType;
import com.example.bookmyshow.Review.execption.ReviewNotFoundException;
import com.example.bookmyshow.Review.helper.Helper;
import com.example.bookmyshow.Review.repository.ReactionRepository;
import com.example.bookmyshow.Review.repository.ReviewRepository;
import com.example.bookmyshow.Review.schema.ReactionReview;
import com.example.bookmyshow.Review.schema.Review;
import com.example.bookmyshow.UserLogin.schema.User;
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
