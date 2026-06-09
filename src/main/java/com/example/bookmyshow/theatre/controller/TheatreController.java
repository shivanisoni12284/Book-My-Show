package com.example.bookmyshow.theatre.controller;

import com.example.bookmyshow.execption.TheatreNotFoundException;
import com.example.bookmyshow.theatre.dto.TheatreRequest;
import com.example.bookmyshow.theatre.schema.Theatre;
import com.example.bookmyshow.theatre.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bookmyshow/theatre")
@RequiredArgsConstructor
@RestController
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody TheatreRequest theatreRequest){
        Theatre createdTheater = theatreService.createTheatre(theatreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheater);
    }

    // find theatre by id
    @GetMapping("/single-theatre/{theaterId}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("theaterId") Long theatreId){
        Theatre theater = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theater);
    }

    //find theatre by city
    @GetMapping("city/{city}")
    public ResponseEntity<List<Theatre>> getAllTheatresByCity(@PathVariable String city){
        List<Theatre> theatres = theatreService.getAllTheatresByCity(city);
        return ResponseEntity.ok(theatres);
    }

    // find all theatres
    @GetMapping("/all-theatre")
    public ResponseEntity<List<Theatre>> findAllTheatre(){
        List<Theatre> theatres = theatreService.findAllTheatre();
        return ResponseEntity.ok(theatres);
    }

    // Delete theatre by id

    @DeleteMapping("{theatreId}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long theatreId){
        theatreService.deleteTheatre(theatreId);
        return ResponseEntity.ok("theatre deleted successfully");
    }
}
