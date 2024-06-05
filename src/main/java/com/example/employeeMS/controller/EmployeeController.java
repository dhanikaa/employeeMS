package com.example.employeeMS.controller;

import com.example.employeeMS.dto.EmployeeDto;
import com.example.employeeMS.dto.ResponseDto;
import com.example.employeeMS.entity.Employee;
import com.example.employeeMS.service.EmployeeService;
import com.example.employeeMS.utility.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String res = employeeService.saveEmployee(employeeDto);
            if (res.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if (res.equals("06")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Duplicated");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Failed");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String res = employeeService.updateEmployee(employeeDto);
            if (res.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Updated");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if (res.equals("01")) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Not a Registered Employee");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Failed");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees() {
        try {
            List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Fetched");
            responseDto.setContent(employeeDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getEmployeeById/{empId}")
    public ResponseEntity getEmployeeById(@PathVariable int empId) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
            if (employeeDto != null) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No Employee Found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteEmployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId) {
        try {
            String res = employeeService.deleteEmployee(empId);
            if (res.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No Employee Found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
