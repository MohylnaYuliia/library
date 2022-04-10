package library.mapper;

import library.dto.UserDto;
import library.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookEntityToDto.class})
public interface UserEntityToDtoMapper {

    List<UserDto> mapToDtoList(List<UserEntity> userEntity);

    UserDto mapToDto(UserEntity userEntity);
}
