package com.wizer.wizerassessment.service.impl;


import com.wizer.wizerassessment.exceptions.ResourceNotFoundException;
import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.models.FavoriteBook;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
import com.wizer.wizerassessment.repositories.BookRepository;
import com.wizer.wizerassessment.repositories.FavoriteBookRepository;
import com.wizer.wizerassessment.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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

    public String addFavoriteBook(Long userId, Long bookId) {
        Book book = repository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book not found"));
        FavoriteBook favoriteBook = new FavoriteBook();
        favoriteBook.setUserId(userId);
        favoriteBook.setBook(book);
        favoriteBookRepository.save(favoriteBook);
        return "saved Favorite Book";
    }

    @Override
    public void deleteBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
