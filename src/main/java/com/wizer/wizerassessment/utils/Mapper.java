package com.wizer.wizerassessment.utils;

import com.wizer.wizerassessment.models.FavoriteBook;
import com.wizer.wizerassessment.payloads.responses.FavoriteBookResponseDto;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<FavoriteBookResponseDto> mapToDto(List<FavoriteBook> favoriteBook){
        List<FavoriteBookResponseDto> responseDto = new ArrayList<>();
        favoriteBook.forEach(favoriteBook1 -> {
            FavoriteBookResponseDto favoriteBookResponseDto = new FavoriteBookResponseDto();
            favoriteBookResponseDto.setName(favoriteBook1.getName());
            responseDto.add(favoriteBookResponseDto);
        });
        return responseDto;
    }
}
