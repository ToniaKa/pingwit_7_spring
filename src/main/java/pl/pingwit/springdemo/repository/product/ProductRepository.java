package pl.pingwit.springdemo.repository.product;

import org.springframework.stereotype.Repository;
import pl.pingwit.springdemo.exception.PingwitException;
import pl.pingwit.springdemo.repository.product.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> findAllProducts() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,description,price FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBigDecimal(4)));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Product> findProductById(Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM products WHERE id='%d'", id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBigDecimal(4)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer createProduct(Product product) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(id,name,description,price) VALUES (?,?,?,?)");
            int id = getNextProductId();
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, product.name());
            preparedStatement.setString(3, product.description());
            preparedStatement.setBigDecimal(4, product.price());
            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product productToUpdate) {
        String updateRequest = """
                UPDATE products
                SET name = ?, description = ?, price = ?
                WHERE id = ?;
                """;
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(updateRequest)) {
            statement.setString(1, productToUpdate.name());
            statement.setString(2, productToUpdate.description());
            statement.setBigDecimal(3, productToUpdate.price());
            statement.setInt(4, productToUpdate.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getNextProductId() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nextval('product_id_seq')");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else throw new PingwitException("id not found!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductById(Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
