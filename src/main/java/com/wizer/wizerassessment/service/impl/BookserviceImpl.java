package com.wizer.wizerassessment.service.impl;


import com.wizer.wizerassessment.exceptions.ResourceNotFoundException;
import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.models.FavoriteBook;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.requests.FavoriteBookRequestDto;
import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
import com.wizer.wizerassessment.payloads.responses.FavoriteBookResponseDto;
import com.wizer.wizerassessment.repositories.BookRepository;
import com.wizer.wizerassessment.repositories.FavoriteBookRepository;
import com.wizer.wizerassessment.service.BookService;
import com.wizer.wizerassessment.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookserviceImpl implements BookService {
    private final ModelMapper modelMapper;

    private final BookRepository repository;

    private final FavoriteBookRepository favoriteBookRepository;
    @Override
    public ResponseEntity addBook(BookRequestDto requestDto) {
        Book book =modelMapper.map(requestDto, Book.class);
       return new ResponseEntity<>(repository.save(book), HttpStatus.CREATED);
    }

    @Override
    public BookResponseDto editBook(BookRequestDto requestDto, Long id) {
        Book book=repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found"));
        book.setIsbn(requestDto.getIsbn());
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        repository.save(book);

        return modelMapper.map(book, BookResponseDto.class);
    }

    @Override
    public Page<Book> listBooks(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return repository.findAll(paging);
    }

    public List<FavoriteBookResponseDto> addFavoriteBooks(List<FavoriteBookRequestDto> books) {
        List<FavoriteBook> favoriteBookList = new ArrayList<>();
        books.forEach(book -> {
            FavoriteBook favoriteBook1 = new FavoriteBook();
            favoriteBook1.setName(book.getName());
            favoriteBookList.add(favoriteBook1);
        });
        List<FavoriteBook> book =favoriteBookRepository.saveAll(favoriteBookList);
        return Mapper.mapToDto(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
