package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DepartmentToDtoConverter;
import com.samsolutions.kitayeu.myproject.converters.DtoToDepartmentConverter;
import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DepartmentDto createDepartmentDto(DepartmentDto departmentDto) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        String departmentNameFromDto = departmentDto.getDepartmentName();
        List<String> departmentNameFromDatabase = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            departmentNameFromDatabase.add(departmentToDtoConverter.convert(department).getDepartmentName());
        }
        if (departmentNameFromDatabase.contains(departmentNameFromDto)) {
            return departmentDto;
        } else {
            Department createdDepartment = departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
            return departmentToDtoConverter.convert(createdDepartment);
        }
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDtos() {
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            departmentDtoList.add(departmentToDtoConverter.convert(department));
        }
        return departmentDtoList;
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartmentDto(DepartmentDto departmentDto) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        Department updatedDepartment = departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
        return departmentToDtoConverter.convert(updatedDepartment);
    }

    @Override
    public DepartmentDto getById(int id) {
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentToDtoConverter.convert(departmentRepository.getReferenceById(id));
    }
}
