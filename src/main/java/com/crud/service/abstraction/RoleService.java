package com.crud.service.abstraction;


import com.crud.model.Role;

public interface RoleService {
    Role get(int id);
    Role getByName(String name);
}
