package library.repository;

import library.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LibraryRepository extends CrudRepository<BookEntity, Integer> {
}
