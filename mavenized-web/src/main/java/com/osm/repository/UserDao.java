package com.osm.repository;

import com.osm.model.User;

/**
 * Data Access Object for User related operations.
 */
public interface UserDao extends GenericDao<User, String> {

    User findUser(String username);
}