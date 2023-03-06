package com.samsolutions.kitayeu.myproject.daos;

import com.samsolutions.kitayeu.myproject.entities.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class DepartmentDAO implements DAO<Department> {

    @PersistenceContext
    private EntityManager entityManager;

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
    @Transactional
    public void save(Department department) {
        entityManager.merge(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        entityManager.merge(department);
    }

    @Override
    @Transactional
    public void delete(Department department) {
        entityManager.remove(entityManager.contains(department) ? department : entityManager.merge(department));
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
