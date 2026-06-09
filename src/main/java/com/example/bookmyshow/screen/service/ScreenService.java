package com.example.bookmyshow.screen.service;

import com.example.bookmyshow.execption.ScreenNotFoundException;
import com.example.bookmyshow.execption.TheatreNotFoundException;
import com.example.bookmyshow.screen.dto.ScreenRequest;
import com.example.bookmyshow.screen.repository.ScreenRepository;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.theatre.repository.TheatreRepository;
import com.example.bookmyshow.theatre.schema.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    //create screen
    public Screen createScreen(ScreenRequest screenRequest){
        Theatre theatre = theatreRepository.findById(screenRequest.getTheatreId()).orElseThrow(() -> new TheatreNotFoundException("Theatre not present with id"));


        Screen screen = Screen.builder()
                .screenName(screenRequest.getScreenName())
                .theatre(theatre)
                .build();

        return screenRepository.save(screen);

    }

    //get screen by screenName
    public Screen getByScreenName(String screenName){
        return screenRepository.findByScreenName(screenName).orElseThrow(() -> new ScreenNotFoundException("screen not found with screen name"));
    }

    //update screen
    public Screen updateScreen(Long screenId,ScreenRequest screenRequest){

        // if screen with id is not present
        Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new ScreenNotFoundException("screen not present with id"));

        // if theatre not present with id
        Theatre theatre = theatreRepository.findById(screenRequest.getTheatreId()).orElseThrow(() -> new TheatreNotFoundException("Theatre not present with id"));

        screen.setScreenName(screenRequest.getScreenName());
        screen.setTheatre(theatre);

        return screenRepository.save(screen);

    }

    // get screen by id
    public Screen getScreenById(Long screenId){
        return screenRepository.findById(screenId).orElseThrow(() -> new ScreenNotFoundException("screen not present with id"));
    }

    // get all screen of Theatre
    public List<Screen> getScreensByTheatre(Long theatreId){

        theatreRepository.findById(theatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre Not Present With Id"));
        return screenRepository.findAllScreensByTheatreId(theatreId);

    }


    // get all screens
    public List<Screen> getAllScreens(){
        return screenRepository.findAll();
    }

    //delete screen by screen id of theatre id
    public void deleteScreenByTheatre(Long screenId,Long theatreId){
        theatreRepository.findById(theatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre not present with id"));

        if(!screenRepository.existsByScreenIdAndTheatreId(screenId,theatreId)){
            throw new ScreenNotFoundException("Screen does not belong to this theatre");
        }
        screenRepository.deleteScreenByTheatreId(screenId,theatreId);
    }

    // delete screen
    public void deleteScreen(Long screenId){
        getScreenById(screenId);
        screenRepository.deleteById(screenId);
    }

}
