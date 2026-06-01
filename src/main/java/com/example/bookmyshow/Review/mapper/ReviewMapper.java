package com.example.bookmyshow.Review.mapper;

import com.example.bookmyshow.Review.dtos.ReviewResponse;
import com.example.bookmyshow.Review.schema.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


//    if  fields are same mapstruct will automatically create ex:-  @Mapping(source = "id" ,target = "id")

    @Mapping(source = "movie.movieName", target = "movieName")
    @Mapping(source = "user.userName", target = "userName")

    ReviewResponse toDto(Review review);
}
