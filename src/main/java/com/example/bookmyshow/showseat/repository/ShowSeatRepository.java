package com.example.bookmyshow.showseat.repository;

import com.example.bookmyshow.seat.schema.Seat;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    boolean existsByShowAndSeat(Show show, Seat seat);

    List<ShowSeat> findByShow(Show show);

    List<ShowSeat> findByShowAndBookedIsFalse(Show show);
}
