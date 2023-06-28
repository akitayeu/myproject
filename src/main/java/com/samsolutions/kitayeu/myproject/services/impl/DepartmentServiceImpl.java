package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DepartmentToDtoConverter;
import com.samsolutions.kitayeu.myproject.converters.DtoToDepartmentConverter;
import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.exceptions.DeleteOrChangeEntityNotAllowException;
import com.samsolutions.kitayeu.myproject.exceptions.EntityDuplicateException;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        Department createdDepartment = new DtoToDepartmentConverter().convert(departmentDto);

        if (departmentRepository.existsByDepartmentName(createdDepartment.getDepartmentName())) {
            throw new EntityDuplicateException("409");
        } else {
            departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
            return departmentToDtoConverter.convert(createdDepartment);
        }
    }

    @Override
    public List<DepartmentDto> getAllDepartments(int page) {
        Pageable paging = PageRequest.of(page, pageSize);
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentRepository.findAll(paging).stream()
                .map(departmentToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        if (id != 0) {
            List<Employee> employeeList = employeeRepository.findEmployeesByDepartment(departmentRepository.findById(id).get());
            if (employeeList.size() != 0) {
                for (Employee employee : employeeList) {
                    employee.setDepartment(departmentRepository.findById(0).get());
                    employeeRepository.save(employee);
                }
                departmentRepository.deleteById(id);
            } else {
                departmentRepository.deleteById(id);
            }
        } else {
            throw new DeleteOrChangeEntityNotAllowException("403");
        }
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, int id) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        Department updatedDepartment = new DtoToDepartmentConverter().convert(departmentDto);
        if (id != 0) {
            if (departmentRepository.existsByDepartmentName(updatedDepartment.getDepartmentName())) {
                throw new EntityDuplicateException("409");
            } else {
                departmentDto.setDepartmentId(id);
                departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
                return departmentToDtoConverter.convert(updatedDepartment);
            }
        } else {
            throw new DeleteOrChangeEntityNotAllowException("403");
        }
    }

    @Override
    public DepartmentDto getById(int id) {
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentToDtoConverter.convert(departmentRepository.getReferenceById(id));
    }
}
