package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.TicketType;
import com.ernie.TicketApp.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {

    private DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveUser(User user) {
        String SQLStatement = "INSERT INTO user_info (id, name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, userId.toString());
            preparedStatement.executeUpdate();
            System.out.println("User with ID " + userId + " was deleted from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByTicket(Ticket ticket) {
        String SQLStatement = "DELETE FROM user_info WHERE id = (SELECT user_id FROM ticket_info WHERE id = ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
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

    @Transactional
    public void updateUserAddTicket(UUID id) {
        User user = null;
        Ticket newTicket = new Ticket();
        newTicket.setTicketType(TicketType.MONTH);
        String SQLStatementGetUser = "SELECT * FROM user_info WHERE id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatementGetUser)) {
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

        String SQLStatementSetTicketToUser = "INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatementSetTicketToUser)){
            preparedStatement.setString(1, newTicket.getId().toString());
            preparedStatement.setString(2, user.getId().toString());
            preparedStatement.setString(3, newTicket.getTicketType().name());
            preparedStatement.setString(4, newTicket.getCreationDateTime().toString());
            preparedStatement.executeUpdate();
            System.out.println("Ticket " + newTicket.getId() + " with User " + user.getName() + " was saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQLStatementUpdateUser = "UPDATE user_info SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatementUpdateUser)){
            preparedStatement.setString(1, "new name");
            preparedStatement.setString(2, user.getId().toString());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getId() + " changed name.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}