package com.example.bookmyshow.show.controller;

import com.example.bookmyshow.execption.ShowNotFoundException;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.show.dto.ShowRequest;
import com.example.bookmyshow.show.schema.Show;
import com.example.bookmyshow.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    //create show
    @PostMapping("/createNewShow")
    public ResponseEntity<Show> createShow(@RequestBody ShowRequest showRequest){
        Show createdShow = showService.createShow(showRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShow);
    }

    // get show by id
    @GetMapping("/singleShow/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable Long showId){
        Show showById = showService.getShowById(showId);
       return ResponseEntity.ok(showById);

    }

    // get all shows
    @GetMapping("/allShows")
    public ResponseEntity<List<Show>> getAllShows(){
        List<Show> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    // DeleteShow
    @DeleteMapping("/deleteShow/{showId}")
    public ResponseEntity<String> deleteShow(Long showId){
        showService.deleteShow(showId);
        return ResponseEntity.ok("show with id deleted successfully");

    }

    // update show
    @PutMapping("/updateShow/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable Long showId,@RequestBody ShowRequest showRequest){
        Show updatedShow = showService.updateShow(showId,showRequest);
        return ResponseEntity.ok(updatedShow);
    }

    //get shows by screen
    @GetMapping("/getShowByScreen/{screenId}")
    public ResponseEntity<List<Show>> gwtAllShowsByScreen(@PathVariable Long screenId){
        List<Show> shows = showService.getAllShowsByScreen(screenId);
        return ResponseEntity.ok(shows);
    }
}
