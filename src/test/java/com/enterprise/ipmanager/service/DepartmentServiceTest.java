package com.enterprise.ipmanager.service;

import com.enterprise.ipmanager.dto.DepartmentDto;
import com.enterprise.ipmanager.model.Department;
import com.enterprise.ipmanager.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void testGetAllDepartments() {
        Department department = new Department(1L, "IT", "D001");
        when(departmentRepository.findAll()).thenReturn(Collections.singletonList(department));

        List<DepartmentDto> result = departmentService.getAllDepartments();

        assertEquals(1, result.size());
        assertEquals("IT", result.get(0).getName());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testCreateDepartment() {
        DepartmentDto dtoToCreate = new DepartmentDto();
        dtoToCreate.setName("HR");
        dtoToCreate.setCode("D002");

        Department departmentToSave = new Department(null, "HR", "D002");
        Department savedDepartment = new Department(2L, "HR", "D002");

        when(departmentRepository.save(any(Department.class))).thenReturn(savedDepartment);

        DepartmentDto result = departmentService.createDepartment(dtoToCreate);

        assertEquals(2L, result.getId());
        assertEquals("HR", result.getName());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }
}