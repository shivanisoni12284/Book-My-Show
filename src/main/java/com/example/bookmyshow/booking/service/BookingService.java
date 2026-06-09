package com.example.bookmyshow.booking.service;

import com.example.bookmyshow.booking.dto.BookingRequestDto;
import com.example.bookmyshow.booking.repository.BoookingRepository;
import com.example.bookmyshow.booking.schema.BookedStatus;
import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.execption.SeatAlreadyExistsException;
import com.example.bookmyshow.execption.TheatreNotFoundException;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.show.service.ShowService;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import com.example.bookmyshow.showseat.service.ShowSeatService;
import com.example.bookmyshow.theatre.schema.Theatre;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BoookingRepository boookingRepository;
    private final ShowService showService;
    private final ShowSeatService showSeatService;

    @Transactional
    public Booking createBooking(BookingRequestDto bookingRequestDto){
        // check show is available or not
        Show show = showService.getShowById(bookingRequestDto.getShow().getShowId());

        //seat A1 A2 A3
        List<ShowSeat> bookedSeats = new ArrayList<>();
        double totalPrice = 0;

        for(ShowSeat seatRequest:bookingRequestDto.getShowSeats()){
            // Fetch latest seat state from DB
            ShowSeat dbSeat = showSeatService.getShowSeatById(
                    seatRequest.getId());

            if(dbSeat.getBooked()){
                throw new SeatAlreadyExistsException("seat already booked:"+dbSeat.getSeat().getSeatNumber());
            }
            // Mark seat as booked
            dbSeat.setBooked(true);

            bookedSeats.add(dbSeat);

            // Calculate total price from backend
            totalPrice += dbSeat.getPrice();

        }

        Booking booking = Booking.builder()
                .booked(BookedStatus.CONFIRMED)
                .show(show)
                .showSeats(bookedSeats)
                .totalPrice(bookingRequestDto.getTotalPrice())
                .build();

        return boookingRepository.save(booking);
    }

    // get booking by id
    public Booking getBookingById(Long bookingId){
        return boookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("booking not found with id: "+bookingId));
    }

    //find theatre by city
    public List<Booking> getAllBookings(){
        return boookingRepository.findAll();
    }

    // Delete theatre by id
    public void deleteBooking(Long bookingId){
        Booking booking = getBookingById(bookingId);
        boookingRepository.delete(booking);
    }
}
