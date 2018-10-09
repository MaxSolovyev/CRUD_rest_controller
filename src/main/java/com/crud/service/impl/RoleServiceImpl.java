package com.crud.service.impl;

import com.crud.dao.abstraction.RoleDao;
import com.crud.model.Role;
import com.crud.service.abstraction.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao userDao) {
        this.roleDao = userDao;
    }

    @Override
    public Role get(int id) {
        return roleDao.get(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }
}
