package com.crud.dao.impl;

import com.crud.dao.abstraction.UserDao;
import com.crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id=:id", User.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.login=:login", User.class
        );
        query.setParameter("login", login);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);

    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }
}
