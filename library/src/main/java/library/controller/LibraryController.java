package library.controller;

import library.entity.BookEntity;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    public List<BookEntity> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/books/{bookId}/users/{userId}")
    public void borrowBook(@PathVariable Integer bookId, @PathVariable Integer userId) {
        libraryService.borrowBook(userId, bookId);
    }

    @DeleteMapping("/books/{bookId}/users/{userId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer userId) {
        libraryService.borrowBook(userId, bookId);
    }

    @DeleteMapping("/books/users/{userId}")
    public void returnALLBook(@PathVariable Integer userId) {
        libraryService.borrowBook(userId, 0);
    }
}
