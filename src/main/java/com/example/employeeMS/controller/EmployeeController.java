package com.example.employeeMS.controller;

import com.example.employeeMS.dto.EmployeeDto;
import com.example.employeeMS.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {

        }
        catch (Exception e) {

        }
    }
}
