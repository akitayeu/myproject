package com.samsolutions.kitayeu.myproject.dao;

import com.samsolutions.kitayeu.myproject.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RoleDAO implements DAO<Role> {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDAO() {
    }

    @Override
    public Optional<Role> get(int id) {
        return Optional.ofNullable(entityManager.find(Role.class, id));
    }

    @Override
    public List<Role> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Role e");
        return query.getResultList();
    }

    @Override
    public void save(Role role) {
        executeInsideTransaction(entityManager -> entityManager.persist(role));
    }

    @Override
    public void update(Role role) {
        executeInsideTransaction(entityManager -> entityManager.merge(role));
    }

    @Override
    public void delete(Role role) {
        executeInsideTransaction(entityManager -> entityManager.remove(role));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
