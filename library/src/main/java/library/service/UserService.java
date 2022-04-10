package library.service;

import library.dto.UserDto;
import library.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
}
