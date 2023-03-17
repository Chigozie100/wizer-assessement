package com.wizer.wizerassessment.service;


import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.models.FavoriteBook;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.requests.FavoriteBookRequestDto;
import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
import com.wizer.wizerassessment.payloads.responses.FavoriteBookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    ResponseEntity addBook(BookRequestDto requestDto);
    BookResponseDto editBook(BookRequestDto requestDto, Long id);
    Page<Book> listBooks(int pageNo, int pageSize, String sortBy);

    List<FavoriteBookResponseDto> addFavoriteBooks(List<FavoriteBookRequestDto> books);

    void deleteBook(Long bookId);

}
