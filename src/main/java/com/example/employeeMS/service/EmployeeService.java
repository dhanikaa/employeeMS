package com.example.employeeMS.service;

import com.example.employeeMS.dto.EmployeeDto;
import com.example.employeeMS.entity.Employee;
import com.example.employeeMS.repo.EmployeeRepo;
import com.example.employeeMS.utility.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDto employeeDto) {
        if (employeeRepo.existsById(employeeDto.getEmpId())){
            return VarList.RSP_DUPLICATED;
        }
        else {
            employeeRepo.save(modelMapper.map(employeeDto, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
