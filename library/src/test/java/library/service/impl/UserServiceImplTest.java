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
        BookEntity book = BookEntity.builder().id(1).name("Book").existed(true).copy(1).build();
        bookRepository.save(book);
        userRepository.save(UserEntity.builder().id(1).name("name").books(new HashSet<>(Arrays.asList(book))).build());

        List<UserDto> users = userService.getAllUsers();
        Assertions.assertEquals("name", users.get(0).getName());
        Assertions.assertEquals(1, users.get(0).getId());

        List<BookDto> bookDtos = new ArrayList<>(users.get(0).getBooks());
        Assertions.assertEquals(1, bookDtos.get(0).getId());
        Assertions.assertEquals("Book", bookDtos.get(0).getName());
    }

}