package com.crud.service.impl;

import com.crud.dao.abstraction.UserDao;
import com.crud.model.User;
import com.crud.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
