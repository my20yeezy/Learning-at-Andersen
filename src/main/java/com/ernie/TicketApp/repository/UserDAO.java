package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.config.ConnectionConfig;
import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    public void saveUser(User user) {
        String SQLStatement = "INSERT INTO user_info (id, name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionConfig.getConnection().prepareStatement(SQLStatement)){
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCreationDateTime().toString());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getName() + " was saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(UUID id) {
        User user = null;
        String SQLStatement = "SELECT * FROM user_info WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionConfig.getConnection().prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                user = new User(name);
                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setCreationDateTime(LocalDateTime.parse(resultSet.getString("creation_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUserById(UUID userId) {
        String SQLStatement = "DELETE FROM user_info WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionConfig.getConnection().prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, userId.toString());
            preparedStatement.executeUpdate();
            System.out.println("User with ID " + userId + " was deleted from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByTicket(Ticket ticket) {
        String SQLStatement = "DELETE FROM user_info WHERE id = (SELECT user_id FROM ticket_info WHERE id = ?)";
        try (PreparedStatement preparedStatement = ConnectionConfig.getConnection().prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, ticket.getId().toString());
            preparedStatement.executeUpdate();
            System.out.println("User with ticket ID " + ticket.getId() + " was deleted from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String SQLStatement = "SELECT * FROM user_info";
        try (PreparedStatement preparedStatement = ConnectionConfig.getConnection().prepareStatement(SQLStatement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"));
                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setCreationDateTime(LocalDateTime.parse(resultSet.getString("creation_date")));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Got " + allUsers.size() + " Users from DB.");
        return allUsers;
    }

}