package pl.pingwit.springdemo.repository;

import java.math.BigDecimal;

public record Product(Integer id, String name, String description, BigDecimal price) {
}
