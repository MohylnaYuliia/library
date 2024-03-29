package library.repository;

import library.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    List<BookEntity> findByExistedTrue();

    Optional<BookEntity> findByIdAndExistedTrue(int id);
}
