package pl.pingwit.springdemo.validator;

import org.junit.jupiter.api.Test;
import pl.pingwit.springdemo.controller.user.CreateUserInputDto;
import pl.pingwit.springdemo.exception.PingwitValidationException;
import pl.pingwit.springdemo.repository.user.User;
import pl.pingwit.springdemo.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserValidatorTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserValidator target = new UserValidator(userRepository);

    @Test
    void shouldThrowPingwitValidationExceptionWhenEmailExists() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("test");
        inputDto.setSurname("test");
        inputDto.setEmail("test.com");
        inputDto.setPhone("214");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenNameIsBlank() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("");
        inputDto.setSurname("test");
        inputDto.setEmail("test.com");
        inputDto.setPhone("214");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenSurnameIsBlank() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("test");
        inputDto.setSurname("");
        inputDto.setEmail("test.com");
        inputDto.setPhone("214");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenEmailIsInvalid() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("test");
        inputDto.setSurname("test");
        inputDto.setEmail(".com");
        inputDto.setPhone("214");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenPhoneNotOnlyDigits() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("test");
        inputDto.setSurname("test");
        inputDto.setEmail("yey@.com");
        inputDto.setPhone("214tttt");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
    }

    @Test
    void shouldErrorsMessageWhenInvaledInput() {
        CreateUserInputDto inputDto = new CreateUserInputDto();
        inputDto.setName("");
        inputDto.setSurname("");
        inputDto.setEmail(".com");
        inputDto.setPhone("214grey");

        when(userRepository.findUserByEmail(any()))
                .thenReturn(List.of(new User(null, null, null, null, null)));

        List<String> errorsMessage = new ArrayList<>();
        errorsMessage.add("Name is blank");
        errorsMessage.add("Surname is blank");
        errorsMessage.add("Email is invalid");
        errorsMessage.add("Phone can contain only numbers");
        errorsMessage.add("Email " + inputDto.getEmail() + " already exists");

        PingwitValidationException exception = assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(inputDto));
        assertThat(exception.getMessage()).isEqualTo("User data is invalid: ", errorsMessage);
    }
}