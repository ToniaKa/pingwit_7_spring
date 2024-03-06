package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.CreateProductInputDto;
import pl.pingwit.springdemo.controller.ProductDto;
import pl.pingwit.springdemo.converter.ProductConverter;
import pl.pingwit.springdemo.repository.Product;
import pl.pingwit.springdemo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAllProducts().stream()
                .map(productConverter::convertToProductDto)
                .toList();
    }

    public ProductDto findProductById(Integer id) {
        Optional<Product> productById = productRepository.findProductById(id);
        // Вынесите методы конвертации из сервисов в конвертеры.
        // под этой фразой я имел в виду только преобразование из User в UserDto, из CreateProductInputDto в Product и подобное
        // в этотм случае оюа конверетера не нужндаются в репозиториях, они будут такими "глуповатыми" классами


        return productById.map(productConverter::convertToProductDto)
                .orElseThrow(() -> new IllegalArgumentException("Product not found!"));

    }

    public Integer createProduct(CreateProductInputDto input) {
        Product product = productConverter.convertToProduct(input);
        return productRepository.createProduct(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProductById(id);
    }
}
