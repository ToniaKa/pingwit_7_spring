package pl.pingwit.springdemo.validator;

import org.junit.jupiter.api.Test;
import pl.pingwit.springdemo.controller.product.CreateProductInputDto;
import pl.pingwit.springdemo.exception.PingwitValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductValidatorTest {
    private final ProductValidator target = new ProductValidator();

    @Test
    void shouldThrowPingwitValidationExceptionWhenNameIsCharacter() {

        CreateProductInputDto input = new CreateProductInputDto();

        input.setName("$");
        input.setDescription("test");
        input.setPrice(BigDecimal.TEN);

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenDescriptionIsCharacter() {
        CreateProductInputDto input = new CreateProductInputDto();
        input.setName("test");
        input.setDescription("$");
        input.setPrice(BigDecimal.TEN);

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenNameIsBlank() {

        CreateProductInputDto input = new CreateProductInputDto();
        input.setName("");
        input.setDescription("test");
        input.setPrice(BigDecimal.TEN);

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenDescriptionIsBlank() {
        CreateProductInputDto input = new CreateProductInputDto();
        input.setName("test");
        input.setDescription("");
        input.setPrice(BigDecimal.TEN);

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));
    }

    @Test
    void shouldErrorsMessageWhenInvaledInput() {
        CreateProductInputDto input = new CreateProductInputDto();
        input.setName("");
        input.setName("$");
        input.setDescription("");
        input.setDescription("$");
        input.setPrice(BigDecimal.ZERO);
        input.setPrice(new BigDecimal("-5"));

        List<String> errorsMessage = new ArrayList<>();
        errorsMessage.add("Name is blank");
        errorsMessage.add("Name contains invalid character");
        errorsMessage.add("Description is blank");
        errorsMessage.add("Description contains invalid character");
        errorsMessage.add("Price is 0");
        errorsMessage.add("Price is a negative number");

        PingwitValidationException exception = assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));

        assertThat(exception.getMessage()).isEqualTo("Product data is invalid: ", errorsMessage);
    }

    @Test
    void shouldThrowPingwitValidationExceptionWhenPriceIsZero() {
        CreateProductInputDto input = new CreateProductInputDto();
        input.setName("test");
        input.setDescription("test");
        input.setPrice(BigDecimal.ZERO);

        assertThrows(PingwitValidationException.class, () -> target.validateOnCreate(input));
    }
}
