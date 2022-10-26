package com.samsolutions.kitayeu.myproject.dao;

import com.samsolutions.kitayeu.myproject.entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;
import java.util.List;
import java.util.function.Consumer;

public class DepartmentDAO implements DAO<Department> {
    @PersistenceContext
    private EntityManager entityManager;

    public DepartmentDAO() {
    }

    @Override
    public Optional<Department> get(int id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));
    }

    @Override
    public List<Department> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Department e");
        return query.getResultList();
    }

    @Override
    public void save(Department department) {
        executeInsideTransaction(entityManager -> entityManager.persist(department));
    }

    @Override
    public void update(Department department) {
        executeInsideTransaction(entityManager -> entityManager.merge(department));
    }

    @Override
    public void delete(Department department) {
        executeInsideTransaction(entityManager -> entityManager.remove(department));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
