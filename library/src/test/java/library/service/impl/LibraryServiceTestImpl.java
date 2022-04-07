package library.service.impl;

import library.Application;
import library.entity.BookEntity;
import library.repository.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryServiceTestImpl {

    private static final String FIRST_BOOK_NAME = "First Book";
    private static final int FIRST_BOOK_ID = 1;

    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    public void testWhenNoBooksInLibrary() {
        Assertions.assertEquals(0, ((Collection<?>) libraryRepository.findAll()).size());

        Assertions.assertEquals(0, libraryService.getAllBooks().size());
    }

    @Test
    public void testWhenThereAreBooksInLibrary() {
        libraryRepository.save(BookEntity.builder().id(FIRST_BOOK_ID).name(FIRST_BOOK_NAME).build());
        Assertions.assertEquals(1, ((Collection<?>) libraryRepository.findAll()).size());

        Assertions.assertEquals(1, libraryService.getAllBooks().size());
        Assertions.assertEquals(FIRST_BOOK_NAME, libraryService.getAllBooks().get(0).getName());

        libraryRepository.deleteAll();
    }


}
