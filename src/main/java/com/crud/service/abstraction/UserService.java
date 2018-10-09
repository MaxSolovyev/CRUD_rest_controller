package com.crud.service.abstraction;


import com.crud.model.User;

import java.util.List;

public interface UserService {
    User get(long id);
    User getByLogin(String login);
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> getAll();
}
