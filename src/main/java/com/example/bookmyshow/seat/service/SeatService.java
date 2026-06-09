package com.example.bookmyshow.seat.service;

import com.example.bookmyshow.execption.SeatAlreadyExistsException;
import com.example.bookmyshow.execption.SeatNotFoundException;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.screen.service.ScreenService;
import com.example.bookmyshow.seat.dto.SeatRequest;
import com.example.bookmyshow.seat.repository.SeatRepository;
import com.example.bookmyshow.seat.schema.Seat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScreenService screenService;

    //create seat
    public Seat createSeat(SeatRequest seatRequest){

        if(seatRepository.existsBySeatNumberAndScreen_ScreenId(seatRequest.getSeatNumber(),seatRequest.getScreenId())){
            throw new SeatAlreadyExistsException("seat already exists");

        }

        Screen screen = screenService.getScreenById(seatRequest.getScreenId());

        Seat seat = Seat.builder()
                .seatNumber(seatRequest.getSeatNumber())
                .seatType(seatRequest.getSeatType())
                .booked(false)  // while creating no one is booking so initially it should be false
                .screen(screen)
                .build();

        return seatRepository.save(seat);
    }

    //update seat
    public Seat updateSeat(Long seatId,SeatRequest seatRequest){
        Seat seat = getSeatById(seatId);

        if (!seat.getSeatNumber().equals(seatRequest.getSeatNumber())
                && seatRepository.existsBySeatNumberAndScreen_ScreenId(
                seatRequest.getSeatNumber(),
                seat.getScreen().getScreenId())) {

            throw new SeatAlreadyExistsException(
                    "Seat already exists in this screen");
        }

       seat.setSeatNumber(seatRequest.getSeatNumber());
       seat.setSeatType(seatRequest.getSeatType());

        return seatRepository.save(seat);
    }

    //available seats
    public List<Seat> availableSeats(){
        return seatRepository.findAllByBookedFalse();

    }

    //booked seats
    public List<Seat> bookedSeats(){
        return seatRepository.findAllByBookedTrue();
    }

    // GET ALL SEAT OF SCREEN
    public List<Seat> getSeatByScreen_Id(Long screenId){
        screenService.getScreenById(screenId);
        return seatRepository.findAllByScreen_ScreenId(screenId);
    }

    // get seat by id
    public Seat getSeatById(Long seatId){
        return seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException("seat not found"));
    }

    //delete seat
    public void deleteSeat(Long seatId){
        Seat seat = getSeatById(seatId);
        seatRepository.delete(seat);
    }

    //book Seat
    public Seat bookSeat(Long seatId){
        Seat seat = getSeatById(seatId);
        if(seat.isBooked()){
            throw new RuntimeException("seat already booked");
        }
        seat.setBooked(true);
        return seatRepository.save(seat);

    }

    //cancel booking
    public Seat cancelBooking(Long seatId){
        Seat seat = getSeatById(seatId);
        if(!seat.isBooked()){
            throw new RuntimeException("seat is already avilable");
        }
        seat.setBooked(false);
       return seatRepository.save(seat);
    }

    //check seat exists for screen
    public boolean  validateSeatsExistForScreen(Long screenId){
        screenService.getScreenById(screenId);

        if(!seatRepository.existsByScreen_ScreenId( screenId)){
            throw new SeatNotFoundException("seat not found");
        }
        return true;
    }


}
