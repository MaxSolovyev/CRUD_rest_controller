package com.crud.dao.abstraction;

import com.crud.model.Role;
import com.crud.model.User;

import java.util.List;

public interface RoleDao {
    Role get(int id);
    Role getByName(String name);
}
