package pl.pingwit.springdemo.controller.product;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.springdemo.repository.product.ProductEntity;
import pl.pingwit.springdemo.service.ProductEntityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductEntityController {
    private final ProductEntityService productEntityService;

    public ProductEntityController(ProductEntityService productServiceEntity) {
        this.productEntityService = productServiceEntity;
    }

    @GetMapping
    public List<ProductEntity> findAllProducts() {
        return productEntityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> findProductById(@PathVariable(name = "id") Integer id) {
        return productEntityService.findProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody UpdateProductInputDto inputDto, @PathVariable(name = "id") Integer id) {
        productEntityService.updateProduct(id, inputDto);
    }

    @PostMapping
    public Integer createProduct(@RequestBody CreateProductInputDto input) {
        return productEntityService.createProduct(input);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable(name = "id") Integer id) {
        productEntityService.deleteProduct(id);
    }
}
