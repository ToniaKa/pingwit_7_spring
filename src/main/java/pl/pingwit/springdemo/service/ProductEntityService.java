package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.product.CreateProductInputDto;
import pl.pingwit.springdemo.controller.product.UpdateProductInputDto;
import pl.pingwit.springdemo.exception.PingwitException;
import pl.pingwit.springdemo.repository.product.ProductEntity;
import pl.pingwit.springdemo.repository.product.ProductEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {

    private final ProductEntityRepository productEntityRepository;

    public ProductEntityService(ProductEntityRepository productRepositoryEntity) {
        this.productEntityRepository = productRepositoryEntity;
    }

    public List<ProductEntity> findAll() {
        return productEntityRepository.findAll();
    }

    public Optional<ProductEntity> findProductById(Integer id) {
        return productEntityRepository.findById(id);
    }

    public void updateProduct(Integer id, UpdateProductInputDto inputDto) {
        ProductEntity productToUpdate = productEntityRepository.findById(id).orElseThrow(() -> new PingwitException("Product not found"));
        productToUpdate.setName(inputDto.getName());
        productToUpdate.setDescription(inputDto.getDescription());
        productToUpdate.setPrice(inputDto.getPrice());
        productEntityRepository.save(productToUpdate);
    }

    public Integer createProduct(CreateProductInputDto input) {
        ProductEntity productToCreate = new ProductEntity();
        productToCreate.setName(input.getName());
        productToCreate.setDescription(input.getDescription());
        productToCreate.setPrice(input.getPrice());
        productEntityRepository.save(productToCreate);
        return productToCreate.getId();
    }

    public void deleteProduct(Integer id) {
        productEntityRepository.deleteById(id);
    }
}
