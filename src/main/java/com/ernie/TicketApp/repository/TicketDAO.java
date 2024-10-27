package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.TicketType;
import com.ernie.TicketApp.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TicketDAO {

    private DataSource dataSource;

    public TicketDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveTicket(Ticket ticket, User user) {
        String SQLStatement = "INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)){
            preparedStatement.setString(1, ticket.getId().toString());
            preparedStatement.setString(2, user.getId().toString());
            preparedStatement.setString(3, ticket.getTicketType().name());
            preparedStatement.setString(4, user.getCreationDateTime().toString());
            preparedStatement.executeUpdate();
            System.out.println("Ticket " + ticket.getId() + " with User " + user.getName() + " was saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getTicketById(UUID ticketId) {
        Ticket ticket = null;
        String SQLStatement = "SELECT * FROM ticket_info WHERE id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
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
        String SQLStatement = "UPDATE ticket_info SET ticket_type = ?::ticket_type WHERE id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)){
            preparedStatement.setString(1, ticketType.toString());
            preparedStatement.setString(2, ticket.getId().toString());
            preparedStatement.executeUpdate();
            System.out.println("Ticket " + ticket.getId() + " changed Type to " + ticketType + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        String SQLStatement = "SELECT * FROM ticket_info";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQLStatement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(UUID.fromString(resultSet.getString("id")));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setCreationDateTime(LocalDateTime.parse(resultSet.getString("creation_date")));
                allTickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Got " + allTickets.size() + " Tickets from DB.");
        return allTickets;
    }

}