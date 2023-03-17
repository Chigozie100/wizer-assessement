package com.wizer.wizerassessment.controllers;


import com.wizer.wizerassessment.models.Category;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.requests.CategoryRequestDto;
import com.wizer.wizerassessment.payloads.responses.CategoryResponseDto;
import com.wizer.wizerassessment.service.CategoryService;
import com.wizer.wizerassessment.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody CategoryRequestDto requestDto){
        return  new ResponseEntity<>(service.addCategory(requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/editCategory/{id}")
    public ResponseEntity<CategoryResponseDto> editCategory(@RequestBody CategoryRequestDto requestDto,
                                                            @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.editCategory(requestDto, id), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> getCategories(@RequestParam(defaultValue = Constants.PAGENO) Integer pageNo,
                                                        @RequestParam(defaultValue = Constants.PAGESIZE) Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy) {
        Page<Category> pagedResult = service.listCategories(pageNo, pageSize, sortBy);

        if(pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST http://localhost:8080/categories/1/books
    @PostMapping("/{category}")
    public String addBooksToCategory(@PathVariable (name = "category") String categoryName,
                                                   @RequestBody List<BookRequestDto> books) {
        service.addBooksToCategory(categoryName, books);
        ResponseEntity.status(HttpStatus.CREATED);
        return "Book successfully added";
    }
}
