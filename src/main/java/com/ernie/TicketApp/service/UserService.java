package com.ernie.TicketApp.service;

import com.ernie.TicketApp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    User getUserById(UUID id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserById(UUID id);

    void updateUserAndAddTicket(User user);
}
