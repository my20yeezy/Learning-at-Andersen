package com.ernie.app.ticket;


import com.ernie.app.ticket.model.Ticket;
import com.ernie.app.ticket.model.User;
import com.ernie.app.ticket.repository.TicketRepository;
import com.ernie.app.ticket.repository.UserRepository;
import com.ernie.app.ticket.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private User user;
    private UUID ticketId;

    @BeforeEach
    void setUp() {
        ticketId = UUID.randomUUID();
        ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setPrice(20);

        user = new User("TestUserName");
        user.setId(UUID.randomUUID());

    }

    @Test
    void saveTicket_positiveTest() {
        ticketService.isUserUpdateAndTicketCreationEnabled = true;

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.saveTicket(ticket);

        assertNotNull(savedTicket);
        assertEquals(ticketId, savedTicket.getId());
        verify(ticketRepository, times(1)).save(ticket);
    }
    @Test
    void saveTicket_negativeTest_creationDisabled() {
        ticketService.isUserUpdateAndTicketCreationEnabled = false;

        assertThrows(IllegalArgumentException.class, () -> ticketService.saveTicket(ticket));
        verify(ticketRepository, never()).save(ticket);
    }
    @Test
    void saveTicket_cornerCase_nullTicket() {
        assertThrows(NullPointerException.class, () -> ticketService.saveTicket(null));
        verify(ticketRepository, never()).save(ticket);
    }


    @Test
    void getTicketById_positiveTest() {
        when(ticketRepository.getReferenceById(ticketId)).thenReturn(ticket);

        Ticket foundTicket = ticketService.getTicketById(ticketId);

        assertNotNull(foundTicket);
        assertEquals(ticketId, foundTicket.getId());
        verify(ticketRepository, times(1)).getReferenceById(ticketId);
    }
    @Test
    void getTicketById_negativeTest_nonExistentId() {
        UUID nonExistentId = UUID.randomUUID();
        when(ticketRepository.getReferenceById(nonExistentId)).thenReturn(null);

        Ticket result = ticketService.getTicketById(nonExistentId);

        assertEquals(result, null);
        assertNull(result);
        verify(ticketRepository, times(1)).getReferenceById(nonExistentId);
    }
    @Test
    void getTicketById_cornerCase_nullId() {
        assertThrows(NullPointerException.class, () -> ticketService.getTicketById(null));
        verify(ticketRepository, never()).getReferenceById(ticketId);
    }


    @Test
    void getAllTickets_positiveTest() {
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<Ticket> tickets = ticketService.getAllTickets();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        assertEquals(ticket, tickets.get(0));
        verify(ticketRepository, times(1)).findAll();
    }
    @Test
    void getAllTickets_negativeTest_emptyList() {
        when(ticketRepository.findAll()).thenReturn(List.of());

        List<Ticket> tickets = ticketService.getAllTickets();

        assertTrue(tickets.isEmpty());
        assertEquals(0, tickets.size());
        verify(ticketRepository, times(1)).findAll();
    }
    @Test
    void getAllTickets_cornerCase_nullList() {
        when(ticketRepository.findAll()).thenReturn(null);

        List<Ticket> tickets = ticketService.getAllTickets();

        assertNull(tickets);
        verify(ticketRepository, times(1)).findAll();
    }


    @Test
    void updateTicket_positiveTest() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        ticketService.updateTicket(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }
    @Test
    void updateTicket_negativeTest_nullTicket() {
        assertThrows(NullPointerException.class, () -> ticketService.updateTicket(null));
        verify(ticketRepository, never()).save(null);
    }
    @Test
    void updateTicket_cornerCase_ticketNotExist() {
        when(ticketRepository.save(ticket)).thenReturn(null);

        ticketService.updateTicket(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }


    @Test
    void deleteTicket_positiveTest() {
        ticketService.deleteTicket(ticket);

        verify(ticketRepository, times(1)).delete(ticket);
    }
    @Test
    void deleteTicket_negativeTest_nullTicket() {
        assertThrows(NullPointerException.class, () -> ticketService.deleteTicket(null));
        verify(ticketRepository, never()).delete(null);
    }
    @Test
    void deleteTicket_cornerCase_ticketNotExist() {
        doThrow(new IllegalArgumentException("Ticket not found")).when(ticketRepository).delete(ticket);

        assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicket(ticket));
        verify(ticketRepository, times(1)).delete(ticket);
    }


    @Test
    void deleteTicketById_positiveTest() {
        ticketService.deleteTicketById(ticketId);

        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
    @Test
    void deleteTicketById_negativeTest_nonExistentId() {
        UUID nonExistentId = UUID.randomUUID();
        doThrow(new IllegalArgumentException("Ticket ID not found")).when(ticketRepository).deleteById(nonExistentId);

        assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicketById(nonExistentId));
        verify(ticketRepository, times(1)).deleteById(nonExistentId);
    }
    @Test
    void deleteTicketById_cornerCase_nullId() {
        assertThrows(NullPointerException.class, () -> ticketService.deleteTicketById(null));
        verify(ticketRepository, never()).deleteById(null);
    }


    @Test
    void setTicketToUser_positiveTest() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(userRepository.save(user)).thenReturn(user);

        ticketService.setTicketToUser(ticket, user);

        assertEquals(user, ticket.getUser());
        assertTrue(user.getTickets().contains(ticket));
        verify(ticketRepository, times(1)).save(ticket);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void setTicketToUser_negativeTest_nullTicket() {
        assertThrows(NullPointerException.class, () -> ticketService.setTicketToUser(null, user));
        verify(ticketRepository, never()).save(null);
        verify(userRepository, never()).save(user);
    }
    @Test
    void setTicketToUser_cornerCase_nullUser() {
        assertThrows(NullPointerException.class, () -> ticketService.setTicketToUser(ticket, null));
        verify(ticketRepository, never()).save(ticket);
        verify(userRepository, never()).save(null);
    }


    @Test
    void getTicketByUser_positiveTest() {
        ticket.setUser(user);
        when(ticketRepository.findByUser(user)).thenReturn(ticket);

        Ticket foundTicket = ticketService.getTicketByUser(user);

        assertNotNull(foundTicket);
        assertEquals(user, foundTicket.getUser());
        verify(ticketRepository, times(1)).findByUser(user);
    }
    @Test
    void getTicketByUser_negativeTest_userWithNoTicket() {
        when(ticketRepository.findByUser(user)).thenReturn(null);

        Ticket result = ticketService.getTicketByUser(user);

        assertNull(result);
        verify(ticketRepository, times(1)).findByUser(user);
    }
    @Test
    void getTicketByUser_cornerCase_nullUser() {
        assertThrows(NullPointerException.class, () -> ticketService.getTicketByUser(null));
        verify(ticketRepository, never()).findByUser(null);
    }

}