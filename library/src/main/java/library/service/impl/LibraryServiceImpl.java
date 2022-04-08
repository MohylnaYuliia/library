package library.service.impl;

import library.entity.BookEntity;
import library.entity.UserEntity;
import library.repository.BookRepository;
import library.repository.UserRepository;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBooksList = new ArrayList<>();
        bookRepository.findAll().forEach(allBooksList::add);
        return allBooksList;
    }

    @Override
    public void borrowBook(int userId, int bookId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        userEntity.getBookEntitySet().add(bookEntity);
        bookEntity.setExisted(false);

        userRepository.save(userEntity);
    }
}
