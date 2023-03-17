package com.wizer.wizerassessment.service;


import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity addBook(BookRequestDto requestDto);
    BookResponseDto editBook(BookRequestDto requestDto, Long id);
    Page<Book> listBooks(int pageNo, int pageSize, String sortBy);

    String addFavoriteBook(Long userId, Long bookId);

    void deleteBook(Long bookId);

}
