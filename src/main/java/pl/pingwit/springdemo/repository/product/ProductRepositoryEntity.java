package pl.pingwit.springdemo.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepositoryEntity extends JpaRepository<ProductEntity, Integer> {

}
