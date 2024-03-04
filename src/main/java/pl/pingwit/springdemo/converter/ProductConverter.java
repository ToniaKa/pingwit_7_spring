package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.CreateProductInputDto;
import pl.pingwit.springdemo.repository.Product;
import pl.pingwit.springdemo.repository.ProductRepository;

@Component
public class ProductConverter {
    private final ProductRepository productRepository;

    public ProductConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Integer createProduct(CreateProductInputDto input) {
        Product product = new Product(null, input.getName(), input.getDescription(), input.getPrice());
        return productRepository.createProduct(product);
    }
}
