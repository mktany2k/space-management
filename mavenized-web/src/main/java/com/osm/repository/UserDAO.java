package com.osm.repository;

import com.osm.model.User;
import java.util.List;

/**
 * Data Access Object for User related operations.
 */
public interface UserDAO {

    User getUser(Long userId);

    User findUser(String username);

    void createUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    void updateUser(User user);
}