package com.wizer.wizerassessment.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;


@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Long isbn;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Category category;

    @JsonManagedReference
    public Category getCategory() {
        return category;
    }
}
