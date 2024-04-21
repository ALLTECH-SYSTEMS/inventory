package com.inventory.book.config;

import com.inventory.book.entity.Book;
import com.inventory.book.entity.User;
import com.inventory.book.enums.Genre;
import com.inventory.book.repository.BookRepository;
import com.inventory.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeBooks();
        initializeUsers();
    }

    private void initializeBooks() {
        if (bookRepository.count() == 0) {
            List<Book> books = Arrays.asList(
                    new Book("George Orwell", Genre.FICTION, "978-0451524935", "1984", 1949, 20.00),
                    new Book("Agatha Christie", Genre.MYSTERY, "978-0007136834", "Murder on the Orient Express", 1934, 15.00),
                    new Book("Edgar Allan Poe", Genre.HORROR, "978-0486266848", "The Tell-Tale Heart", 1843, 10.00),
                    new Book("William Shakespeare", Genre.POETRY, "978-0743477123", "Sonnets", 1609, 18.00),
                    new Book("Kurt Vonnegut", Genre.SATIRE, "978-0385333481", "Slaughterhouse-Five", 1969, 22.00)
            );
            bookRepository.saveAll(books);
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            List<User> users = Arrays.asList(
                    new User("user1", "hashed_password_1"),
                    new User("user2", "hashed_password_2"),
                    new User("user3", "hashed_password_3")
            );
            userRepository.saveAll(users);
        }
    }
}
