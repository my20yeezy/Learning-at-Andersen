package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.TicketType;
import com.ernie.TicketApp.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketDAO {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/my_ticket_service";
    static final String USER = "postgres";
    static final String PASSWORD = "ernie";
    private Connection connection;

    public TicketDAO() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTicket(Ticket ticket, User user) {
        String SQLStatement = "INSERT INTO \"Ticket\" (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)){
            preparedStatement.setString(1, ticket.getId().toString());
            preparedStatement.setString(2, user.getId().toString());
            preparedStatement.setString(3, ticket.getTicketType().toString());
            preparedStatement.setString(4, user.getCreationDateTime().toString());
            preparedStatement.executeUpdate();
            System.out.println("Ticket " + ticket.getId() + " with User " + user.getName() + " was saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket fetchById(UUID ticketId) {
        Ticket ticket = null;
        String SQLStatement = "SELECT * FROM \"Ticket\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)) {
            preparedStatement.setString(1, ticketId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID userId = UUID.fromString(resultSet.getString("user_id"));
                TicketType ticketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                ticket = new Ticket();
                ticket.setId(UUID.fromString(resultSet.getString("id")));
                ticket.setTicketType(ticketType);
                ticket.setCreationDateTime(LocalDateTime.parse(resultSet.getString("creation_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public void updateTicketType(Ticket ticket, TicketType ticketType) {
        String SQLStatement = "UPDATE \"Ticket\" SET ticket_type = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement)){
            preparedStatement.setString(1, ticketType.toString());
            preparedStatement.setString(2, ticket.getId().toString());
            preparedStatement.executeUpdate();
            System.out.println("Ticket " + ticket.getId() + " changed Type to " + ticketType + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}