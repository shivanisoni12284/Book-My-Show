package com.example.bookmyshow.showseat.controller;


import com.example.bookmyshow.showseat.dto.ShowSeatRequestDto;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import com.example.bookmyshow.showseat.service.ShowSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showSeat")
@RequiredArgsConstructor
public class ShowSeatController {

    private final ShowSeatService showSeatService;

        // create ShowSeat
    @PostMapping("/admin/create-ShowSeat")
    public ResponseEntity<ShowSeat> createShowSeat(ShowSeatRequestDto requestDto){
       ShowSeat showSeat = showSeatService.createShowSeat(requestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(showSeat);
    }

    // get show Seat By Id
    @GetMapping("{showSeatId}")
    public ResponseEntity<ShowSeat> getShowSeatById(Long showSeatId){
       ShowSeat showSeat = showSeatService.getShowSeatById(showSeatId);
       return ResponseEntity.ok(showSeat);
    }

    // get all showSeat
    @GetMapping("/get-allseats")
    public ResponseEntity<List<ShowSeat>> getAllShowSeat(){
        List<ShowSeat> showSeats = showSeatService.getAllShowSeat();
        return ResponseEntity.ok(showSeats);
    }

    // delete show seat
    @DeleteMapping("/deleteMapping")
    public ResponseEntity<String> deleteSeat(Long showSeatId){
       showSeatService.getShowSeatById(showSeatId);
       return ResponseEntity.ok("Show Seat deleted successfully");
    }

    //get showSeat by show
    @GetMapping("/showSeatsByshow/{showId}")
    public ResponseEntity<List<ShowSeat>> getShowSeatsByShow(Long showId){
        List<ShowSeat> showSeats = showSeatService.getShowSeatsByShow(showId);
        return ResponseEntity.ok(showSeats);

    }

    //get all ShowSeat available for show
    @GetMapping("/allavailableseatshow/{showId}")
    public ResponseEntity<List<ShowSeat>> getAvailableaSeatsforShow(Long showId){
        List<ShowSeat> showList = showSeatService.getAvailableSeatsforShow(showId);
        return ResponseEntity.ok(showList);
    }
}
