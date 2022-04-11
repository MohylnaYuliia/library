package library.service;

import library.entity.BookEntity;

import java.util.List;

public interface LibraryService {

    List<BookEntity> getAllBooks();

    void borrowBook(Integer firstUserId, Integer firstBookId);

    void returnBook(Integer firstUserId, Integer bookId);
}
