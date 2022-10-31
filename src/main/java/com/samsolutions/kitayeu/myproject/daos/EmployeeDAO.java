package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class EmployeeDAO implements DAO<Employee> {

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeDAO() {
    }

    @Override
    public Optional<Employee> get(int id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> getAll() {

        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }

    @Override
    public void save(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.persist(employee));
    }

    @Override
    public void update(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.merge(employee));
    }

    @Override
    public void delete(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.remove(employee));
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
