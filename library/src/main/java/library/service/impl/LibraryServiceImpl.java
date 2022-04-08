package library.service.impl;

import library.entity.BookEntity;
import library.entity.UserEntity;
import library.exception.BookNotExistsException;
import library.exception.UserCannotBorrowBookException;
import library.exception.UserNotExistsException;
import library.repository.BookRepository;
import library.repository.UserRepository;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static library.constant.Constants.*;

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
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotExistsException(USER_NOT_EXISTS_MSG));
        BookEntity bookEntity = bookRepository.findByIdAndExistedTrue(bookId).orElseThrow(() -> new BookNotExistsException(BOOK_NOT_EXISTS_MSG));

        if (userEntity.getBookEntitySet().size() >= USER_ALLOWED_NUMBER_OF_BORROWED_BOOKS) {
            throw new UserCannotBorrowBookException(USER_CANNOT_BORROW_BOOK_MSG);
        }
        userEntity.getBookEntitySet().add(bookEntity);
        bookEntity.setExisted(false);

        userRepository.save(userEntity);
    }
}
