package library.mapper;

import library.dto.BookDto;
import library.entity.BookEntity;

import java.util.Set;

public interface BookEntityToDto {

    BookDto mapBookToDto(BookEntity bookEntity);

    Set<BookDto> mapBookToDtoList(Set<BookEntity> bookEntity);
}
