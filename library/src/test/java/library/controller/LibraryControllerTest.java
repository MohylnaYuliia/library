package library.controller;

import library.entity.BookEntity;
import library.service.impl.LibraryServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryServiceImpl service;

    @Test
    public void testGetAllBooks() throws Exception {
        when(service.getAllBooks()).thenReturn(Arrays.asList(BookEntity.builder().id(1).name("name").existed(true).build()));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/library/books/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].*", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("name"));
    }

    @Test
    public void testGetAllBooksWhenThereNoBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/library/books/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));
    }

    @Test
    public void testBorrowBook() throws Exception {
        doNothing().when(service).borrowBook(1,1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/library/books/{bookId}/users/{userId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnOneBook() throws Exception {
        doNothing().when(service).returnBook(1,1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/library/books/{bookId}/users/{userId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnAllBooks() throws Exception {
        doNothing().when(service).returnBook(1,0);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/library/books/users/{userId}",  1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}