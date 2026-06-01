package com.example.bookmyshow.Review.repository;

import com.example.bookmyshow.Review.schema.ReactionReview;
import com.example.bookmyshow.Review.schema.Review;
import com.example.bookmyshow.UserLogin.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionReview,Long> {

    ReactionReview findByUserAndReview(User user, Review review);
}
