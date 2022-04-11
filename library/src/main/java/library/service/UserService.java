package library.service;

import library.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
}
