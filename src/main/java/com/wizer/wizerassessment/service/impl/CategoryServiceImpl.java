package com.wizer.wizerassessment.service.impl;


import com.wizer.wizerassessment.exceptions.ResourceNotFoundException;
import com.wizer.wizerassessment.models.Book;
import com.wizer.wizerassessment.models.Category;
import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
import com.wizer.wizerassessment.payloads.requests.CategoryRequestDto;
import com.wizer.wizerassessment.payloads.responses.CategoryResponseDto;
import com.wizer.wizerassessment.repositories.BookRepository;
import com.wizer.wizerassessment.repositories.CategoryRepository;
import com.wizer.wizerassessment.service.CategoryService;
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

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final BookRepository bookRepository;
    @Override
    public ResponseEntity addCategory(CategoryRequestDto requestDto) {
        Category category=modelMapper.map(requestDto, Category.class);
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    @Override
    public CategoryResponseDto editCategory(CategoryRequestDto requestDto, Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not found"));
        category.setName(requestDto.getName());
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public Page<Category> listCategories(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return categoryRepository.findAll(paging);
    }

    @Override
    public void addBooksToCategory(String categoryName, List<BookRequestDto> books) {
        Category category = categoryRepository.findByNameIgnoreCase(categoryName).orElseThrow(()-> new ResourceNotFoundException("category not found"));
        List<Book> bookList = new ArrayList<>();
        books.forEach(book-> {
            Book book1 = new Book();
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setIsbn(book.getIsbn());
            book1.setCategory(category);
            bookList.add(book1);
        });
        bookRepository.saveAll(bookList);
    }


}

