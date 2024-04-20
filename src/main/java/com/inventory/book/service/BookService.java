package com.inventory.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.book.entity.Book;
import com.inventory.book.repository.BookRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class BookService {
   @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String title, String author, Integer year, String genre) {
        return bookRepository.findAll((Specification<Book>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("author")), author.toLowerCase()));
            }
            if (year != null) {
                predicates.add(criteriaBuilder.equal(root.get("yearOfPublication"), year));
            }
            if (genre != null && !genre.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("genre")), genre.toUpperCase()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
       
    }
}
