package org.papaja.adminify.service;

import org.papaja.adminify.dao.UserDao;
import org.papaja.adminify.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return dao.getUser(username);
    }

    @Transactional
    public User getProfile(Integer id) {
        return dao.getUser(id);
    }

    @Transactional
    public void persist(User user) {
        dao.persist(user);
    }

}
