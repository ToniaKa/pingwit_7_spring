package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.CreateUserInputDto;
import pl.pingwit.springdemo.controller.UserDto;
import pl.pingwit.springdemo.repository.User;
import pl.pingwit.springdemo.repository.UserRepository;

@Component
public class UserConverter {
    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser(CreateUserInputDto input) {
        // Вынесите методы конвертации из сервисов в конвертеры.
        // под этой фразой я имел в виду только преобразование из User в UserDto, из CreateProductInputDto в Product и подобное
        // в этотм случае оюа конверетера не нужндаются в репозиториях, они будут такими "глуповатыми" классами
        User user = new User(null, input.getName(), input.getSurname(), input.getEmail(), input.getPhone());
        return userRepository.createUser(user);
    }

    // в конвертере будут методы типа:
    public UserDto convertToDto(User user) {
        return new UserDto(user.id(), user.name() + " " + user.surname(), user.email());
    }
}
