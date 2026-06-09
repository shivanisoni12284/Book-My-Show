package com.example.bookmyshow.review.repository;

import com.example.bookmyshow.review.schema.ReactionReview;
import com.example.bookmyshow.review.schema.Review;
import com.example.bookmyshow.userlogin.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionReview,Long> {

    ReactionReview findByUserAndReview(User user, Review review);
}
