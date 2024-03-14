package pl.pingwit.springdemo.controller.user;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.springdemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public Integer createUser(@RequestBody CreateUserInputDto input) {
        return userService.createUser(input);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody UpdateUserInputDto inputDto, @PathVariable(name = "id") Integer id) {
        userService.updateUser(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }
}
