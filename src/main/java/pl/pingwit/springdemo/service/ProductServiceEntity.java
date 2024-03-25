package pl.pingwit.springdemo.service;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.repository.product.ProductEntity;
import pl.pingwit.springdemo.repository.product.ProductRepositoryEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceEntity {
    private final ProductRepositoryEntity productRepositoryEntity;

    public ProductServiceEntity(ProductRepositoryEntity productRepositoryEntity) {
        this.productRepositoryEntity = productRepositoryEntity;
    }

    public List<ProductEntity> findAll() {
        return productRepositoryEntity.findAll();
    }

    public Optional<ProductEntity> findProductById(Integer id) {
        return productRepositoryEntity.findById(id);
    }
}
