package com.wizer.wizerassessment.service;


import com.wizer.wizerassessment.models.Category;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.requests.CategoryRequestDto;
import com.wizer.wizerassessment.payloads.responses.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity addCategory(CategoryRequestDto requestDto);
    CategoryResponseDto editCategory(CategoryRequestDto requestDto, Long id);
    Page<Category> listCategories(int pageNo, int pageSize, String sortBy);

    void addBooksToCategory(String categoryName, List<BookRequestDto> books);

}
