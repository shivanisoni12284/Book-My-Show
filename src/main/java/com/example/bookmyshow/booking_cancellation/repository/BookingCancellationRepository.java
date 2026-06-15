package com.example.bookmyshow.booking_cancellation.repository;

import com.example.bookmyshow.booking_cancellation.schema.BookingCancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingCancellationRepository extends JpaRepository<BookingCancellation,Long> {
    Long CountAllCancelledBookings();
}
