package com.osm.repository;

import com.osm.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("userDAO")
@SuppressWarnings("unchecked")
public class HibernateUserDao extends HibernateDao implements UserDao {

    @Override
    public User get(String userId) {
        return (User) getSession().get(User.class, userId);
    }

    @Override
    public User findUser(String username) {
        Assert.hasText(username);
        String query = "from User u where u.username = :username";
        return (User) getSession().createQuery(query).setString("username", username).uniqueResult();
    }

    @Override
    public void save(User user) {
        getSession().save(user);
    }

    @Override
    public List<User> findAll() {
        return getSession().createQuery("from User order by username").list();
    }

    @Override
    public void delete(String userId) {
        User user = get(userId);
        if (user != null) {
            getSession().delete(user);
        }
    }

    @Override
    public void update(User user) {
        getSession().update(user);
    }
}
