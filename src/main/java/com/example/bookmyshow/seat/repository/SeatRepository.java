package com.example.bookmyshow.seat.repository;

import com.example.bookmyshow.seat.schema.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository  extends JpaRepository<Seat,Long> {

    boolean existsBySeatNumber(String seatNumber);

    List<Seat> findAllByBookedFalse();

    List<Seat> findAllByBookedTrue();

    List<Seat> findAllByScreen_ScreenId(Long ScreenId);

    boolean existsBySeatNumberAndScreen_ScreenId(String SeatNumber,Long screenId);

    boolean existsByScreen_ScreenId(Long screenId);

}
