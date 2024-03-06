package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.CreateProductInputDto;
import pl.pingwit.springdemo.controller.ProductDto;
import pl.pingwit.springdemo.repository.Product;

@Component
public class ProductConverter {

    public ProductDto convertToProductDto(Product product) {
        return new ProductDto(product.id(), product.name(), product.description(), product.price());
    }

    public Product convertToProduct(CreateProductInputDto input) {
        return new Product(null, input.getName(), input.getDescription(), input.getPrice());
    }
}
