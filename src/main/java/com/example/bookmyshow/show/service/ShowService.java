package com.example.bookmyshow.show.service;

import com.example.bookmyshow.execption.ShowNotFoundException;
import com.example.bookmyshow.movie.schema.Movie;
import com.example.bookmyshow.movie.service.MovieService;
import com.example.bookmyshow.screen.schema.Screen;
import com.example.bookmyshow.screen.service.ScreenService;
import com.example.bookmyshow.show.dto.ShowRequest;
import com.example.bookmyshow.show.repository.ShowRepository;
import com.example.bookmyshow.show.schema.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final ScreenService screenService;
    private final MovieService movieService;

    // create show
    public Show createShow(ShowRequest showRequest){
        Screen screen  = screenService.getScreenById(showRequest.getScreenId());
        Movie movie = movieService.getMovieWithId(showRequest.getMovieId());

        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .screen(screen)
                .movie(movie)
                .build();

        return showRepository.save(show);

    }

    // get show by id
    public Show getShowById(Long showId){
        return showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException(showId));

    }

    // get all shows
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    // DeleteShow
    public void deleteShow(Long showId){
        Show show = getShowById(showId);
        showRepository.delete(show);

    }

    // update show
    public Show updateShow(Long showId,ShowRequest showRequest){
        Show show = getShowById(showId);
        Movie movie = movieService.getMovieWithId(showRequest.getMovieId());

        Screen screen = screenService.getScreenById(showRequest.getScreenId());

        show.setShowDate(showRequest.getShowDate());
        show.setShowTime(showRequest.getShowTime());
        show.setMovie(movie);
        show.setScreen(screen);

        return showRepository.save(show);
    }

    //find show by screen
    public List<Show> getAllShowsByScreen(Long screenId){
        screenService.getScreenById(screenId);

        return showRepository.findByScreen(screenId);

    }
}
