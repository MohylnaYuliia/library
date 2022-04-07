package library.repository;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;

public interface LibraryRepository extends CrudRepository<Book, Integer> {
}
