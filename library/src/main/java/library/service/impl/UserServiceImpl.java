package library.service.impl;

import library.dto.UserDto;
import library.entity.UserEntity;
import library.mapper.UserEntityToDtoMapper;
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

    @Autowired
    protected UserEntityToDtoMapper mapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return mapper.mapToDtoList(result);
    }
}
