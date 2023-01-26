package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.converters.DepartmentToDtoConverter;
import com.samsolutions.kitayeu.myproject.converters.DtoToDepartmentConverter;
import com.samsolutions.kitayeu.myproject.dtos.DepartmentDto;
import com.samsolutions.kitayeu.myproject.entities.Department;
import com.samsolutions.kitayeu.myproject.exceptions.EntityDuplicateException;
import com.samsolutions.kitayeu.myproject.repositories.DepartmentRepository;
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

    @Override
    @Transactional
    public DepartmentDto createDepartmentDto(DepartmentDto departmentDto) {
        DtoToDepartmentConverter dtoToDepartmentConverter = new DtoToDepartmentConverter();
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        Department createdDepartment = new DtoToDepartmentConverter().convert(departmentDto);
        if (departmentRepository.existsByDepartmentName(createdDepartment.getDepartmentName())) {
            throw new EntityDuplicateException("1000");
        } else {
            departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
            return departmentToDtoConverter.convert(createdDepartment);
        }
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDtos(int page) {
        Pageable paging = PageRequest.of(page, pageSize);
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
        Department updatedDepartment = new DtoToDepartmentConverter().convert(departmentDto);
        if (departmentRepository.existsByDepartmentName(updatedDepartment.getDepartmentName())) {
            throw new EntityDuplicateException("1000");
        } else {
            departmentDto.setDepartmentId(id);
            departmentRepository.save(dtoToDepartmentConverter.convert(departmentDto));
            return departmentToDtoConverter.convert(updatedDepartment);
        }
    }

    @Override
    public DepartmentDto getById(int id) {
        DepartmentToDtoConverter departmentToDtoConverter = new DepartmentToDtoConverter();
        return departmentToDtoConverter.convert(departmentRepository.getReferenceById(id));
    }
}
