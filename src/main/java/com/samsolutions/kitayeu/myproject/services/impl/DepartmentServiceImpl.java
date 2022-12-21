package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DepartmentToDtoConverter;
import com.samsolutions.kitayeu.myproject.converters.DtoToDepartmentConverter;
import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
import com.samsolutions.kitayeu.myproject.services.DepartmentService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private int pageSize=5;
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
    public List<DepartmentDto> getAllDepartmentDtos(int page) {
        Pageable paging = PageRequest.of(page,pageSize);
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentRepository.findAll(paging).stream()
                .map(departmentToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartmentDto(DepartmentDto departmentDto, int id) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        departmentDto.setDepartmentId(id);
        List<String> departmentNameFromDatabase = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            departmentNameFromDatabase.add(departmentToDtoConverter.convert(department).getDepartmentName());
        }
        if (departmentNameFromDatabase.contains(dtoToDepartmentConverter.convert(departmentDto).getDepartmentName())) {
            return null;
        } else {
            Department updatedDepartment = departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
            return departmentToDtoConverter.convert(updatedDepartment);
        }
    }

    @Override
    public DepartmentDto getById(int id) {
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentToDtoConverter.convert(departmentRepository.getReferenceById(id));
    }
}
