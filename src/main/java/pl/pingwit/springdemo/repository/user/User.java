package pl.pingwit.springdemo.repository.user;

public record User(Integer id, String name, String surname, String email, String phone) {
}
