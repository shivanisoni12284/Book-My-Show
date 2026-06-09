package com.example.bookmyshow.seat.controller;

import com.example.bookmyshow.execption.SeatAlreadyExistsException;
import com.example.bookmyshow.execption.SeatNotFoundException;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.seat.dto.SeatRequest;
import com.example.bookmyshow.seat.schema.Seat;
import com.example.bookmyshow.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/seat/")
@RequiredArgsConstructor
@RestController
public class SeatController{
    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody SeatRequest seatRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(seatService.createSeat(seatRequest));
    }

    //update seat
    @PutMapping
    public ResponseEntity<Seat> updateSeat(Long seatId,SeatRequest seatRequest){
      Seat updatedSeat = seatService.updateSeat(seatId,seatRequest);
       return ResponseEntity.ok(updatedSeat);
    }

    //available seats
    @GetMapping("/availableSeats")
    public ResponseEntity<List<Seat>> availableSeats(){
        List<Seat> availableSeats = seatService.availableSeats();
        return ResponseEntity.ok(availableSeats);
    }

    //booked seats
    @GetMapping("/bookedSeats")
    public ResponseEntity<List<Seat>> bookedSeats(){
        List<Seat> bookedSeats = seatService.availableSeats();
        return ResponseEntity.ok(bookedSeats);
    }

    // GET ALL SEAT OF SCREEN
    @GetMapping("/SeatByScreen_Id/{screenId}")
    public ResponseEntity<List<Seat>> getSeatByScreen_Id(@PathVariable Long screenId){
        List<Seat> seats = seatService.getSeatByScreen_Id(screenId);
        return ResponseEntity.ok(seats);
    }

    // get seat by id
    @GetMapping("/SeatByScreen_Id/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long seatId){
      Seat  seat = seatService.getSeatById(seatId);
      return ResponseEntity.ok(seat);
    }

    //delete seat
    @DeleteMapping("/deleteSeat/{seatId}")
    public void deleteSeat(@PathVariable Long seatId){
      seatService.deleteSeat(seatId);
    }

    //book Seat
    @GetMapping("/bookSeat/{seatId}")
    public ResponseEntity<Seat> bookSeat(Long seatId){
        Seat  bookedSeat = seatService.bookSeat(seatId);
        return ResponseEntity.ok(bookedSeat);
    }

    //cancel booking
    @GetMapping("/CancelBooking/{seatId}")
    public ResponseEntity<Seat> cancelBooking(Long seatId){
        Seat  cancelSeat = seatService.bookSeat(seatId);
        return ResponseEntity.ok(cancelSeat);
    }

    //check seat exists for screen
    @GetMapping("/seatExists/{screenId}")
    public ResponseEntity<Boolean>  validateSeatsExistForScreen(Long screenId){
        boolean seatExists = seatService.validateSeatsExistForScreen(screenId);
        return ResponseEntity.ok(seatExists);
    }
}
