package library.service.impl;

import library.entity.BookEntity;
import library.entity.UserEntity;
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

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryServiceTestImpl {

    private static final String FIRST_BOOK_NAME = "First Book";
    private static final int FIRST_BOOK_ID = 1;
    public static final int FIRST_USER_ID = 1;

    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
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
        Assertions.assertEquals(1, bookRepository.findByExistedTrue().size());

        Assertions.assertEquals(1, libraryService.getAllBooks().size());
        Assertions.assertEquals(FIRST_BOOK_NAME, libraryService.getAllBooks().get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testWhenUserCanBorrowBookAndBooksRemovedFromLibrary() {
        bookRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).build());
        Assertions.assertEquals(1, ((Collection<?>) bookRepository.findAll()).size());

        libraryService.borrowBook(FIRST_USER_ID, FIRST_BOOK_ID);

        List<BookEntity> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        Assertions.assertFalse(books.get(0).isExisted());
        Optional<UserEntity> userBooks = userRepository.findById(FIRST_USER_ID);
        Assertions.assertEquals(1, userBooks.get().getBookEntitySet().size());
    }


}
