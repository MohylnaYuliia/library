package library.service.impl;

import library.entity.BookEntity;
import library.repository.LibraryRepository;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBooksList = new ArrayList<>();
        libraryRepository.findAll().forEach(allBooksList::add);
        return allBooksList;
    }
}
