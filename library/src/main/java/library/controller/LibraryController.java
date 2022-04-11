package library.controller;

import library.entity.BookEntity;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static library.constant.Constants.DEFAULT_BOOK_VALUE;

@RestController
@RequestMapping("/library/books")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/{bookId}/users/{userId}")
    public void borrowBook(@PathVariable Integer bookId, @PathVariable Integer userId) {
        libraryService.borrowBook(userId, bookId);
    }

    @DeleteMapping("/{bookId}/users/{userId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer userId) {
        libraryService.returnBook(userId, bookId);
    }

    @DeleteMapping("/users/{userId}")
    public void returnALLBook(@PathVariable Integer userId) {
        libraryService.returnBook(userId, DEFAULT_BOOK_VALUE);
    }
}
