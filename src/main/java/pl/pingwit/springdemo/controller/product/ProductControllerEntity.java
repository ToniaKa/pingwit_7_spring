package pl.pingwit.springdemo.controller.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pingwit.springdemo.repository.product.ProductEntity;
import pl.pingwit.springdemo.service.ProductServiceEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControllerEntity {
    private final ProductServiceEntity productServiceEntity;

    public ProductControllerEntity(ProductServiceEntity productServiceEntity) {
        this.productServiceEntity = productServiceEntity;
    }

    @GetMapping
    public List<ProductEntity> findAllProducts() {
        return productServiceEntity.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> findProductById(@PathVariable(name = "id") Integer id) {
        return productServiceEntity.findProductById(id);
    }
}
