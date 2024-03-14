package pl.pingwit.springdemo.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.springdemo.controller.product.CreateProductInputDto;
import pl.pingwit.springdemo.controller.product.ProductDto;
import pl.pingwit.springdemo.controller.product.UpdateProductInputDto;
import pl.pingwit.springdemo.exception.PingwitException;
import pl.pingwit.springdemo.repository.product.Product;
import pl.pingwit.springdemo.repository.product.ProductRepository;

@Component
public class ProductConverter {
    private final ProductRepository productRepository;

    public ProductConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto convertToProductDto(Product product) {
        return new ProductDto(product.id(), product.name(), product.description(), product.price());
    }

    public Product convertToProduct(CreateProductInputDto input) {
        return new Product(null, input.getName(), input.getDescription(), input.getPrice());
    }

    public Product convertToProductUpdate(Integer id, UpdateProductInputDto inputDto) {
        Product existingProduct = productRepository.findProductById(id)
                .orElseThrow(() -> new PingwitException("Product not found"));
        return new Product(existingProduct.id(), inputDto.getName(), inputDto.getDescription(), inputDto.getPrice());
    }
}
