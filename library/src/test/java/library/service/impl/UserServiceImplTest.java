package library.service.impl;

import library.dto.BookDto;
import library.dto.UserDto;
import library.entity.BookEntity;
import library.entity.UserEntity;
import library.repository.BookRepository;
import library.repository.UserRepository;
import library.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    private static final String BOOK_NAME = "Book";
    private static final String USER_NAME = "name";
    private static final int BOOK_ID = 1;
    private static final int USER_ID = 1;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void testGetAllUsers() {
        BookEntity book = BookEntity.builder().id(BOOK_ID).name(BOOK_NAME).existed(true).copy(1).build();
        bookRepository.save(book);
        userRepository.save(UserEntity.builder().id(USER_ID).name(USER_NAME).books(new HashSet<>(Arrays.asList(book))).build());

        List<UserDto> users = userService.getAllUsers();
        Assertions.assertEquals(USER_NAME, users.get(0).getName());
        Assertions.assertEquals(USER_ID, users.get(0).getId());

        List<BookDto> bookDtos = new ArrayList<>(users.get(0).getBooks());
        Assertions.assertEquals(BOOK_ID, bookDtos.get(0).getId());
        Assertions.assertEquals(BOOK_NAME, bookDtos.get(0).getName());
    }

}