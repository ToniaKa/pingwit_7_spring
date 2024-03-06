package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.CreateUserInputDto;
import pl.pingwit.springdemo.controller.UserDto;
import pl.pingwit.springdemo.repository.User;

@Component
public class UserConverter {

    public UserDto convertToDto(User user) {
        return new UserDto(user.id(), user.name() + " " + user.surname(), user.email());
    }

    public User convertToUser(CreateUserInputDto input) {
        return new User(null, input.getName(), input.getSurname(), input.getEmail(), input.getPhone());
    }
}
