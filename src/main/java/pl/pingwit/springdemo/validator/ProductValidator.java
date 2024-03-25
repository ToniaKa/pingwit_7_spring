package pl.pingwit.springdemo.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.product.CreateProductInputDto;
import pl.pingwit.springdemo.exception.PingwitValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    /* Правила валидации: имя продукта - не пустая строка, не содержит символов $.
     Описание (оно опциональное) - не пустая строка, не содержит символов $. Цена - не отрицательная и не 0.*/
    public void validateOnCreate(CreateProductInputDto inputDto) {
        List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(inputDto.getName())) {
            errors.add("Name is blank");
        }
        if (inputDto.getName().contains("$")) {
            errors.add("Name contains invalid character");
        }
        // изначально в нашей стстеме поле description было опциональным. то есть могло быть null
        // эта проверка не допусти null, что противоречит требованиям. проверку на пустую строку следует делать
        // если описание не null:
        // if (inputDto.getDescription() != null && StringUtils.isBlank(inputDto.getDescription()))
        if (StringUtils.isBlank(inputDto.getDescription())) {
            errors.add("Description is blank");
        }
        if (inputDto.getDescription().contains("$")) {
            errors.add("Description contains invalid character");
        }
        if (inputDto.getPrice().equals(BigDecimal.ZERO)) {
            errors.add("Price is 0");
        }
        if (inputDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Price is a negative number");
        }
        if (!errors.isEmpty()) {
            throw new PingwitValidationException("Product data is invalid: ", errors);
        }
    }
}
