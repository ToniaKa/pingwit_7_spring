package pl.pingwit.springdemo.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.user.CreateUserInputDto;
import pl.pingwit.springdemo.exception.PingwitValidationException;
import pl.pingwit.springdemo.repository.user.User;
import pl.pingwit.springdemo.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserValidator {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\d+");
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validateOnCreate(CreateUserInputDto input) {

        // имя - это не пустая строка
        // фамилия - не пустая строка
        // имэйл - валидная строка
        // имэйлов таких в ситеме нет
        // в телефоне содрежатся только цифры

        List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(input.getName())) {
            errors.add("Name is blank");
        }
        if (StringUtils.isBlank(input.getSurname())) {
            errors.add("Surname is blank");
        }
        if (!EMAIL_PATTERN.matcher(input.getEmail()).matches()) {
            errors.add("Email is invalid: " + input.getEmail());
        }
        if (!PHONE_NUMBER_PATTERN.matcher(input.getPhone()).matches()) {
            errors.add("Phone can contain only numbers: " + input.getPhone());
        }
        List<User> userByEmail = userRepository.findUserByEmail(input.getEmail());

        int size = userByEmail.size(); // можно не вводить эту переменную, а сразу проверять
        //if (userByEmail.size() >= 1) {
        if (size >= 1) {
            errors.add("Email " + input.getEmail() + " already exists");
        }
        if (!errors.isEmpty()) {
            throw new PingwitValidationException("User data is invalid: ", errors);
        }
    }
}
