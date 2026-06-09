package com.example.bookmyshow.review.schema;

import com.example.bookmyshow.review.enums.ReactionType;
import com.example.bookmyshow.userlogin.schema.User;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reactionReview")
@Entity
public class ReactionReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    @NonNull
    private Review review;

//    @ElementCollection(fetch = FetchType.EAGER)  this is only used with list set
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;


}
