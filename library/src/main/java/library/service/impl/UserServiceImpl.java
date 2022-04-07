package library.service.impl;

import library.entity.UserEntity;
import library.repository.UserRepository;
import library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }
}
