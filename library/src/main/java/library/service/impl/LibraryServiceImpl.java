package library.service.impl;

import library.entity.BookEntity;
import library.entity.UserEntity;
import library.exception.BookNotExistsException;
import library.exception.UserCannotBorrowBookException;
import library.exception.UserCannotBorrowSameBookTwice;
import library.exception.UserNotExistsException;
import library.repository.BookRepository;
import library.repository.UserRepository;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static library.constant.Constants.*;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findByExistedTrue();
    }

    @Override
    @Transactional
    public void borrowBook(int userId, int bookId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotExistsException(USER_NOT_EXISTS_MSG));
        BookEntity bookEntity = bookRepository.findByIdAndExistedTrue(bookId).orElseThrow(() -> new BookNotExistsException(BOOK_NOT_EXISTS_MSG));

        if (userEntity.getBookEntitySet().size() >= USER_ALLOWED_NUMBER_OF_BORROWED_BOOKS) {
            throw new UserCannotBorrowBookException(USER_CANNOT_BORROW_BOOK_MSG);
        }
        if (userEntity.getBookEntitySet().stream().anyMatch(book -> book.getId().equals(bookId))) {
            throw new UserCannotBorrowSameBookTwice(USER_CANNOT_BORROW_THE_SAME_BOOK_TWICE_MSG);
        }

        userEntity.getBookEntitySet().add(bookEntity);

        if (bookEntity.getCopy() > 1) {
            bookEntity.setCopy(bookEntity.getCopy() - 1);
        } else {
            bookEntity.setCopy(0);
            bookEntity.setExisted(false);
        }
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void returnBook(Integer userId, Integer bookId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotExistsException(USER_NOT_EXISTS_MSG));
        userEntity.getBookEntitySet().removeIf(book -> book.getId().equals(bookId));

        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotExistsException(BOOK_NOT_EXISTS_MSG));
        if (!bookEntity.isExisted()) {
            bookEntity.setExisted(true);
            bookEntity.setCopy(1);
        } else {
            bookEntity.setCopy(bookEntity.getCopy() + 1);
        }

        userRepository.save(userEntity);
        bookRepository.save(bookEntity);
    }
}
