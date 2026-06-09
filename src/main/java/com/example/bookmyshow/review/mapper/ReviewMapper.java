package com.example.bookmyshow.review.mapper;

import com.example.bookmyshow.review.dtos.ReviewResponse;
import com.example.bookmyshow.review.schema.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


//    if  fields are same mapstruct will automatically create ex:-  @Mapping(source = "id" ,target = "id")

    @Mapping(source = "movie.movieName", target = "movieName")
    @Mapping(source = "user.name", target = "userName")

    ReviewResponse toDto(Review review);
}
