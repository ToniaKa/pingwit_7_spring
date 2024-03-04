package pl.pingwit.springdemo.controller;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.springdemo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable(name = "id") Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Integer createProduct(@RequestBody CreateProductInputDto input) {
        return productService.createProduct(input);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.deleteProduct(id);
    }
}
