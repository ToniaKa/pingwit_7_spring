package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.CreateUserInputDto;
import pl.pingwit.springdemo.controller.UserDto;
import pl.pingwit.springdemo.converter.UserConverter;
import pl.pingwit.springdemo.repository.User;
import pl.pingwit.springdemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> findAll() {
        return userRepository.findAllUsers().stream()
                .map(userConverter::convertToDto)
                .toList();
    }

    public UserDto findUserById(Integer id) {
        Optional<User> userById = userRepository.findUserById(id);
        return userById.map(userConverter::convertToDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

    }

    public Integer createUser(CreateUserInputDto input) {
        User user = userConverter.convertToUser(input);
        return userRepository.createUser(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }
}
