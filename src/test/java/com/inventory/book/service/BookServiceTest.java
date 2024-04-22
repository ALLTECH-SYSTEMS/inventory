package com.inventory.book.service;

import com.inventory.book.entity.Book;
import com.inventory.book.enums.Genre;
import com.inventory.book.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Mocking the repository

    @InjectMocks
    private BookService bookService; // Injecting the mocked repository into the service

    @Test
    public void testGetBookById_Found() {
        // Given
        Long bookId = 1L;
        Book expectedBook = new Book("George Orwell", Genre.FICTION, "978-0451524935", "1984", 2021, 9.99);
        expectedBook.setId(bookId); // Set ID manually if your constructor doesn't
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        // When
        Book actualBook = bookService.getBookById(bookId);

        // Then
        assertNotNull(actualBook, "The fetched book should not be null");
        assertEquals(expectedBook.getId(), actualBook.getId(), "The fetched book ID should match the requested ID");
    }

    @Test
    public void testGetBookById_NotFound() {
        // Given
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.getBookById(bookId);
        }, "A RuntimeException should be thrown if no book is found");

        assertTrue(exception.getMessage().contains("Book not found with id: " + bookId), "Exception message should contain the correct book ID");
    }

    @Test
    public void testSearchBooks_WithAllCriteria() {
        // Setup
        String title = "1984";
        String author = "George Orwell";
        Integer year = 1949;
        String genre = "FICTION";
        when(bookRepository.findAll(any(Specification.class))).thenAnswer(invocation -> {
            // Logic to assert the correct Specification is being constructed
            return Arrays.asList(new Book(author, Genre.FICTION, "978-0451524935", title, year, 20.00));
        });

        // Execute
        List<Book> results = bookService.searchBooks(title, author, year, genre);

        // Verify
        assertNotNull(results);
        assertEquals(1, results.size());
        Book result = results.get(0);
        assertEquals(title, result.getTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(year, result.getYearOfPublication());
    }
}
