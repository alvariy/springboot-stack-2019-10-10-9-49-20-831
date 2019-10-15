package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private List<Employee> employeeList = new ArrayList();

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody List<Employee> getEmployee(@PathVariable int id)
    {
        List<Employee> searchedEmployeeList = employeeList.stream().filter(o -> o.getId() == id).collect(Collectors.toList());
        return searchedEmployeeList;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody List<Employee> getEmployeeList()
    {
        return employeeList;
    }

    @PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
    public List<Employee> createRecord(@RequestBody List<Employee> employees)
    {
        employeeList.addAll(employees);
        return employeeList;
    }

    @DeleteMapping(path = "/deleteEmployee/{id}", consumes = "application/json", produces = "application/json")
    public List<Employee> deleteRecord(@PathVariable int id)
    {

        employeeList.removeIf(o -> o.getId() == id);
        employeeList = employeeList.stream().filter(o -> o.getId() != id).collect(Collectors.toList());

        return employeeList;
    }

    @PutMapping(path = "/updateEmployee/{id}", consumes = "application/json", produces = "application/json")
    public List<Employee> updateRecord(@PathVariable int id, @RequestBody Employee employee)
    {

        employeeList = employeeList.stream().map(o ->
        {
         if(o.getId() == id)
         {
             return employee;
         }
         return o;
        }).collect(Collectors.toList());

        return employeeList;
    }
}
