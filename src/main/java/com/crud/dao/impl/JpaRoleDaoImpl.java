package com.crud.dao.impl;

import com.crud.dao.abstraction.RoleDao;
import com.crud.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional(readOnly = true)
public class JpaRoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role get(int id) {
        TypedQuery<Role> query = entityManager.createQuery(
                "select r from Role r where r.id=:id", Role.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "select r from Role r where r.name=:name", Role.class
        );
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }
}