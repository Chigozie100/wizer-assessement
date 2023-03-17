package com.wizer.wizerassessment.repositories;


import com.wizer.wizerassessment.models.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Long> {
}
