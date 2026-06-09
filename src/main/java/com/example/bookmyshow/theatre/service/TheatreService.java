package com.example.bookmyshow.theatre.service;

import com.example.bookmyshow.execption.TheatreNotFoundException;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.screen.service.ScreenService;
import com.example.bookmyshow.theatre.dto.TheatreRequest;
import com.example.bookmyshow.theatre.repository.TheatreRepository;
import com.example.bookmyshow.theatre.schema.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final ScreenService screenService;

    // create theatre
    public Theatre createTheatre(TheatreRequest theatreRequest){
//        if(theatreRequest.getScreenName() == null ||
//                theatreRequest.getScreenName().isEmpty()) {
//            throw new RuntimeException("Screen names are required");
//        }
//
//        List<Screen> screens = new ArrayList<>();
//        for(String name:theatreRequest.getScreenName()){
//           Screen screen = screenService.getByScreenName(name);
//
//            if(screen == null){
//                throw new RuntimeException("Screen not found : " + name);
//            }
//             screens.add(screen);
//        }


        Theatre createdTheatre = Theatre.builder()
                .city(theatreRequest.getCity())
                .theatreName(theatreRequest.getTheatreName())
                .state(theatreRequest.getState())
                .email(theatreRequest.getEmail())
                .pincode(theatreRequest.getPincode())
//                .screens(screens)
                .contact(theatreRequest.getContact())
                .address(theatreRequest.getAddress())
                .build();

        return theatreRepository.save(createdTheatre);
    }

    // find theatre by id
    public Theatre getTheatreById( Long theatreId){
        return theatreRepository.findById(theatreId).orElseThrow(() -> new TheatreNotFoundException("theatre not found with id: "+theatreId));
    }

    //find theatre by city
    public List<Theatre> getAllTheatresByCity(String city){
        return theatreRepository.findByCity(city);
    }

    // find all theatres
    public List<Theatre> findAllTheatre(){
        return theatreRepository.findAll();
    }

    // Delete theatre by id
    public void deleteTheatre(Long theatreId){
        Theatre theatre = getTheatreById(theatreId);
        theatreRepository.delete(theatre);
    }
}
