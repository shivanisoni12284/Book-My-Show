package com.example.bookmyshow.screen.repository;

import com.example.bookmyshow.screen.schema.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen,Long> {

    void deleteScreenByTheatreId(Long screenId,Long theatreId);

    boolean existsByScreenIdAndTheatreId(Long screenId,Long theatreId);

    List<Screen > findAllScreensByTheatreId(Long theatreId);

    Optional<Screen> findByScreenName(String screenName);
}
