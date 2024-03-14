package pl.pingwit.springdemo.controller.user;

import java.util.Objects;

public class UpdateUserInputDto {
    private String surname;
    private String email;
    private String phone;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateUserInputDto that = (UpdateUserInputDto) o;
        return Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, email, phone);
    }
}
