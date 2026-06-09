package com.example.bookmyshow.showseat.service;


import com.example.bookmyshow.execption.ShowSeatAlreadyExistsException;
import com.example.bookmyshow.execption.ShowSeatNotFoundException;
import com.example.bookmyshow.seat.schema.Seat;
import com.example.bookmyshow.seat.service.SeatService;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.show.service.ShowService;
import com.example.bookmyshow.showseat.dto.ShowSeatRequestDto;
import com.example.bookmyshow.showseat.repository.ShowSeatRepository;
import com.example.bookmyshow.showseat.schema.ShowSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

// kisi ik seat seat ke liye user show select kar raha hai
public class ShowSeatService {

    private final ShowSeatRepository showSeatRepository;
    private final ShowService showService;
    private final SeatService seatService;

    // create ShowSeat
    public ShowSeat createShowSeat(ShowSeatRequestDto requestDto){
        Show show = showService.getShowById(requestDto.getShowId());

        Seat seat = seatService.getSeatById(requestDto.getSeatId());

        if(showSeatRepository.existsByShowAndSeat(show,seat)){
            throw new ShowSeatAlreadyExistsException("seat already assigned to this show");
        }

        ShowSeat showSeat = ShowSeat.builder()
                .show(show)
                .seat(seat)
                .price(requestDto.getPrice())
                .booked(false)
                .build();

        return showSeatRepository.save(showSeat);
    }

    // get show Seat By Id
    public ShowSeat getShowSeatById(Long showSeatId){
       return showSeatRepository.findById(showSeatId).orElseThrow(()-> new ShowSeatNotFoundException("Show Seat not found"));

    }

    // get all showSeat
    public List<ShowSeat> getAllShowSeat(){
        return showSeatRepository.findAll();

    }

    // delete show seat
    public void deleteSeat(Long showSeatId){


        ShowSeat showSeat = getShowSeatById(showSeatId);
        if(showSeat.getBooked()){
            throw new RuntimeException("cant delete booked showSeat");
        }
        showSeatRepository.delete(showSeat);
    }

    //get showSeat by show
    public List<ShowSeat> getShowSeatsByShow(Long showId){
        Show show = showService.getShowById(showId);
        return showSeatRepository.findByShow(show);

    }

    //get all ShowSeat available for show
    public List<ShowSeat> getAvailableSeatsforShow(Long showId){
        Show show = showService.getShowById(showId);
        return showSeatRepository.findByShowAndBookedIsFalse(show);
    }

}
