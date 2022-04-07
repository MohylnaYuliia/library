package library.service;

import library.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();
}
