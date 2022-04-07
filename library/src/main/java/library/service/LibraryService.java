package library.service;

import library.entity.BookEntity;

import java.util.List;

public interface LibraryService {

    List<BookEntity> getAllBooks();
}
