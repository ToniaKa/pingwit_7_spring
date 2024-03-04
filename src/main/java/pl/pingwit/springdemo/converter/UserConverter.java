package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.CreateUserInputDto;
import pl.pingwit.springdemo.repository.User;
import pl.pingwit.springdemo.repository.UserRepository;

@Component
public class UserConverter {
    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser(CreateUserInputDto input) {
        User user = new User(null, input.getName(), input.getSurname(), input.getEmail(), input.getPhone());
        return userRepository.createUser(user);
    }
}
