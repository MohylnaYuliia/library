package library.service.impl;

import library.entity.BookEntity;
import library.entity.UserEntity;
import library.exception.BookNotExistsException;
import library.exception.UserCannotBorrowBookException;
import library.exception.UserNotExistsException;
import library.repository.BookRepository;
import library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryServiceTestImpl {

    private static final String FIRST_BOOK_NAME = "First Book";
    private static final int FIRST_BOOK_ID = 1;
    private static final int FIRST_USER_ID = 1;
    private static final int SECOND_BOOK_ID = 2;
    private static final String SECOND_BOOK_NAME = "Second book name";
    private static final int THIRD_BOOK_ID = 3;

    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void testWhenNoBooksInLibrary() {
        Assertions.assertEquals(0, bookRepository.findByExistedTrue().size());

        Assertions.assertEquals(0, libraryService.getAllBooks().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenThereAreBooksInLibrary() {
        bookRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).existed(true).build());
        bookRepository.save(BookEntity.builder().id(SECOND_BOOK_ID).name(SECOND_BOOK_NAME).existed(false).build());
        Assertions.assertEquals(1, bookRepository.findByExistedTrue().size());

        Assertions.assertEquals(1, libraryService.getAllBooks().size());
        Assertions.assertEquals(FIRST_BOOK_NAME, libraryService.getAllBooks().get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenUserCanBorrowBookAndNUmberOfCopiesIsLess() {
        bookRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).existed(true).copy(2).build());
        userRepository.save(UserEntity.builder().id(FIRST_USER_ID).name("John").build());

        libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);

        List<BookEntity> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        Assertions.assertTrue(books.get(0).isExisted());
        Assertions.assertEquals(1, books.get(0).getCopy());
        Optional<UserEntity> userBooks = userRepository.findById(FIRST_USER_ID);
        Assertions.assertEquals(1, userBooks.get().getBookEntitySet().size());
    }

    @Test
    @Transactional
    @Rollback
    @ExceptionHandler
    public void testWhenUserCannotBorrowMoreThanTwoBooks() {
        bookRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).existed(true).build());
        bookRepository.save(BookEntity.builder().id(SECOND_BOOK_ID).name(SECOND_BOOK_NAME).existed(true).build());
        bookRepository.save(BookEntity.builder().id(THIRD_BOOK_ID).name("Third book").existed(true).build());

        userRepository.save(UserEntity.builder().id(FIRST_USER_ID).name("John").build());

        libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);
        libraryService.borrowBook(FIRST_USER_ID, SECOND_BOOK_ID);

        UserCannotBorrowBookException exception = Assertions.assertThrows(UserCannotBorrowBookException.class, () -> {
            libraryService.borrowBook(FIRST_USER_ID, THIRD_BOOK_ID);
        });

        Assertions.assertEquals("User cannot borrow more than 2 books", exception.getMessage());

        List<BookEntity> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        Assertions.assertFalse(books.get(0).isExisted());
        Assertions.assertFalse(books.get(1).isExisted());
        Assertions.assertTrue(books.get(2).isExisted());

        Optional<UserEntity> userBooks = userRepository.findById(FIRST_USER_ID);
        Assertions.assertEquals(2, userBooks.get().getBookEntitySet().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenUserNotExists() {
        UserNotExistsException exception = Assertions.assertThrows(UserNotExistsException.class, () -> {
            libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);
        });

        Assertions.assertEquals("User not exists", exception.getMessage());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenBookNotExists() {
        userRepository.save(UserEntity.builder().id(FIRST_USER_ID).name("John").build());

        BookNotExistsException exception = Assertions.assertThrows(BookNotExistsException.class, () -> {
            libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);
        });

        Assertions.assertEquals("Book not exists", exception.getMessage());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenBookNotAvailableInLibrary() {
        userRepository.save(UserEntity.builder().id(FIRST_USER_ID).name("John").build());
        bookRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).existed(false).build());

        BookNotExistsException exception = Assertions.assertThrows(BookNotExistsException.class, () -> {
            libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);
        });

        Assertions.assertEquals("Book not exists", exception.getMessage());
    }
}
