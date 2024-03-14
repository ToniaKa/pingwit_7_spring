package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.user.CreateUserInputDto;
import pl.pingwit.springdemo.controller.user.UpdateUserInputDto;
import pl.pingwit.springdemo.controller.user.UserDto;
import pl.pingwit.springdemo.exception.PingwitException;
import pl.pingwit.springdemo.repository.user.User;
import pl.pingwit.springdemo.repository.user.UserRepository;

@Component
public class UserConverter {

    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user.id(), user.name() + " " + user.surname(), user.email());
    }

    public User convertToUser(CreateUserInputDto input) {
        return new User(null, input.getName(), input.getSurname(), input.getEmail(), input.getPhone());
    }

    public User convertToUserUpdate(Integer id, UpdateUserInputDto inputDto) {
        User existingUser = userRepository.findUserById(id)
                .orElseThrow(() -> new PingwitException("User not found"));
        return new User(existingUser.id(), existingUser.name(), inputDto.getSurname(), inputDto.getEmail(), inputDto.getPhone());
    }
}
