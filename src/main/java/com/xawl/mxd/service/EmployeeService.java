package com.xawl.mxd.service;

import com.xawl.mxd.bean.Employee;
import com.xawl.mxd.controller.EmployeeController;

import java.util.List;

public interface EmployeeService{

    List<Employee> getAll();

    void saveEmp(Employee employee);

    boolean checkUser(String empName);

    Employee selectEmployeeById(Integer id);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void deleteBatch(List ids);
}
