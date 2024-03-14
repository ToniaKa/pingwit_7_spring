package pl.pingwit.springdemo.repository.user;

import org.springframework.stereotype.Repository;
import pl.pingwit.springdemo.exception.PingwitException;
import pl.pingwit.springdemo.repository.user.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAllUsers() {

        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("""
                    SELECT id, name, surname, email, phone
                    FROM users;
                    """);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
                );
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<User> findUserById(Integer id) {

        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            String request = String.format("""
                    SELECT id, name, surname, email, phone
                    FROM users
                    WHERE id =%d;
                    """, id);
            ResultSet rs = statement.executeQuery(request);
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
                );
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findUserByEmail(String email) {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT *FROM users WHERE email ='%s'", email));
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> usersEmail = new ArrayList<>();

            while (resultSet.next()) {
                usersEmail.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
            return usersEmail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer createUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (id, name, surname, email, phone) VALUES (? , ?, ?, ?, ?)");

            int id = getNextUserId();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, user.name());
            preparedStatement.setString(3, user.surname());
            preparedStatement.setString(4, user.email());
            preparedStatement.setString(5, user.phone());

            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getNextUserId() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nextval('user_id_seq')");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else throw new PingwitException("id not found!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User userToUpdate) {
        // update user by id
        String updateRequest = """
                UPDATE users
                SET surname = ?, email = ?, phone = ?
                WHERE id = ?;
                """;
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(updateRequest)) {
            statement.setString(1, userToUpdate.surname());
            statement.setString(2, userToUpdate.email());
            statement.setString(3, userToUpdate.phone());
            statement.setInt(4, userToUpdate.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
