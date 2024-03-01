package pl.pingwit.springdemo.repository;

public record User(Integer id, String name, String surname, String email, String phone) {
}
