package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.UserDto;
import pl.pingwit.springdemo.repository.User;
import pl.pingwit.springdemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserDto> findAll(){
        return userRepository.findAllUsers().stream()
                .map(user -> new UserDto(user.id(), user.name() + " " + user.surname(), user.email()))
                .toList();
    }
    public UserDto findUserById (Integer id){
        Optional<User> userById = userRepository.findUserById(id);
        return userById.map(user -> new UserDto(user.id(), user.name() + " " + user.surname(), user.email()))
                .orElseThrow(()->new IllegalArgumentException("User not found"));

    }
}
