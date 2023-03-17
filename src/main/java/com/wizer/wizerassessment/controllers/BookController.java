package com.wizer.wizerassessment.controllers;


import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
import com.wizer.wizerassessment.service.BookService;
import com.wizer.wizerassessment.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody BookRequestDto requestDto){
        return  new ResponseEntity<>(service.addBook(requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/editBook/{id}")
    public ResponseEntity<BookResponseDto> editBook(@RequestBody BookRequestDto requestDto,
                                                    @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.editBook(requestDto, id), HttpStatus.OK);
    }

    //GET /users?pageNo=0&pageSize=10&sortBy=id
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getBooks(@RequestParam(defaultValue = Constants.PAGENO) Integer pageNo,
                                               @RequestParam(defaultValue = Constants.PAGESIZE) Integer pageSize,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        Page<Book> pagedResult = service.listBooks(pageNo, pageSize, sortBy);

        if(pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/favorite-books/{bookId}")
    public ResponseEntity<String> addFavoriteBook(@PathVariable Long userId, @PathVariable Long bookId) {
        service.addFavoriteBook(userId, bookId);
        return new ResponseEntity<>(service.addFavoriteBook(userId, bookId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteCategory(@PathVariable Long bookId) {
        service.deleteBook(bookId);
        return "book deleted";
    }

}
