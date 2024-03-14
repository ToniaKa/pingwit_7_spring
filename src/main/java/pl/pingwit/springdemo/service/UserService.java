package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.user.CreateUserInputDto;
import pl.pingwit.springdemo.controller.user.UpdateUserInputDto;
import pl.pingwit.springdemo.controller.user.UserDto;
import pl.pingwit.springdemo.converter.UserConverter;
import pl.pingwit.springdemo.exception.PingwitNotFoundException;
import pl.pingwit.springdemo.repository.user.User;
import pl.pingwit.springdemo.repository.user.UserRepository;
import pl.pingwit.springdemo.validator.UserValidator;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserConverter userConverter, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userValidator = userValidator;
    }

    public List<UserDto> findAll() {
        return userRepository.findAllUsers().stream()
                .map(userConverter::convertToDto)
                .toList();
    }

    public UserDto findUserById(Integer id) {
        Optional<User> userById = userRepository.findUserById(id);
        return userById.map(userConverter::convertToDto)
                .orElseThrow(() -> new PingwitNotFoundException("User not found"));

    }

    public Integer createUser(CreateUserInputDto input) {
        userValidator.validateOnCreate(input);
        User user = userConverter.convertToUser(input);
        return userRepository.createUser(user);
    }

    public void updateUser(Integer id, UpdateUserInputDto inputDto) {
        User userToUpdate = userConverter.convertToUserUpdate(id, inputDto);
        userRepository.updateUser(userToUpdate);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }
}
