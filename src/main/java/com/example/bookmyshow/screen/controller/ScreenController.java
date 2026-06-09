package com.example.bookmyshow.screen.controller;

import com.example.bookmyshow.execption.ScreenNotFoundException;
import com.example.bookmyshow.execption.TheatreNotFoundException;
import com.example.bookmyshow.screen.dto.ScreenRequest;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.screen.service.ScreenService;
import com.example.bookmyshow.theatre.schema.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmyshow/screen")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping
    public ResponseEntity<Screen> createScreen(@RequestBody ScreenRequest screenRequest){

        Screen screen= screenService.createScreen(screenRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(screen);
    }

    //update screen
    @PutMapping("/{screenId}")
    public ResponseEntity<Screen> updateScreen(@PathVariable Long screenId,@RequestBody ScreenRequest screenRequest){
        Screen updatedScreen = screenService.updateScreen(screenId,screenRequest);
        return ResponseEntity.ok(updatedScreen);
    }

    // get screen by id
    @GetMapping("/{screenId}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long screenId){
        Screen screen = screenService.getScreenById(screenId);
        return ResponseEntity.ok(screen);
    }

    // get all screen of Theatre
    @GetMapping("/theatreId/{theatreId}")
    public ResponseEntity<List<Screen>> getScreensByTheatre(Long theatreId){
        List<Screen> screens = screenService.getScreensByTheatre(theatreId);
        return  ResponseEntity.ok(screens);

    }


    // get all screens
    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens(){
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);

    }

    // delete screen
    @DeleteMapping("/screenId")
    public ResponseEntity<String> deleteScreen(Long screenId){
         screenService.deleteScreen(screenId);
         return ResponseEntity.ok("screen deleted successfully");


    }

}
