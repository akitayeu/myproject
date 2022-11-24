package com.samsolutions.kitayeu.myproject.services.Implementations;

import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        Department createdDepartment = departmentRepository.saveAndFlush(department);
        return createdDepartment;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public Department getById(int id) {
        return departmentRepository.getReferenceById(id);
    }
}
