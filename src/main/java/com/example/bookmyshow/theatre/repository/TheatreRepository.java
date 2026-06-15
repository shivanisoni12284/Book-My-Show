package com.example.bookmyshow.theatre.repository;

import com.example.bookmyshow.theatre.schema.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {

    List<Theatre> findByCity(String city);

    Theatre findByMostBooked();
}
