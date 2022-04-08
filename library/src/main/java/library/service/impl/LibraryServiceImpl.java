package library.service.impl;

import library.entity.BookEntity;
import library.repository.BookRepository;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBooksList = new ArrayList<>();
        bookRepository.findAll().forEach(allBooksList::add);
        return allBooksList;
    }

    @Override
    public void borrowBook(int firstUserId, int firstBookId) {

    }
}
