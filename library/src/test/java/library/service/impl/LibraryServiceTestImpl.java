package library.service.impl;

import library.Application;
import library.repository.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class LibraryServiceTestImpl {

    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private LibraryRepository libraryRepository;


    @Test
    public void testWhenNoBooksInLibrary() {
        Assertions.assertEquals(0, ((Collection<?>) libraryRepository.findAll()).size());

        Assertions.assertEquals(0, libraryService.getAllBooks().size());
    }


}
