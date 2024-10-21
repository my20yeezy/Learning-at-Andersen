package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDAO {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/my_ticket_service";
    static final String USER = "postgres";
    static final String PASSWORD = "ernie";
    private Connection connection;

    public UserDAO() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user) {
        String SQLStatement = "INSERT INTO \"User\" (id, name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)){
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCreationDateTime().toString());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getName() + " was saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User fetchById(UUID id) {
        User user = null;
        String SQLStatement = "SELECT * FROM \"User\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                user = new User(name);
                user.setId(id);
                user.setCreationDateTime(LocalDateTime.parse(resultSet.getString("creation_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUserById(UUID userId) {
        String SQLStatement = "DELETE FROM \"User\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, userId.toString());
            preparedStatement.executeUpdate();
            System.out.println("User with ID " + userId + " was deleted from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByTicket(Ticket ticket) {
        String SQLStatement = "DELETE FROM \"User\" WHERE id = (SELECT user_id FROM \"Ticket\" WHERE id = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, ticket.getId().toString());
            preparedStatement.executeUpdate();
            System.out.println("User with ticket ID " + ticket.getId() + " was deleted from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}