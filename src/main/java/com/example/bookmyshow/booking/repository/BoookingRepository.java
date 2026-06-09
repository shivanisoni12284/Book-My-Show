package com.example.bookmyshow.booking.repository;

import com.example.bookmyshow.booking.schema.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoookingRepository extends JpaRepository<Booking,Long> {


}
