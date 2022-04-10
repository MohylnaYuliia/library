package library.mapper;

import library.dto.BookDto;
import library.entity.BookEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookEntityToDto {

    Set<BookDto> map(Set<BookEntity> bookEntity);
}
