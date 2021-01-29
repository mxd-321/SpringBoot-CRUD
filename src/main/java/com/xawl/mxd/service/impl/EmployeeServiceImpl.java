package com.xawl.mxd.service.impl;

import com.xawl.mxd.bean.Employee;
import com.xawl.mxd.bean.EmployeeExample;
import com.xawl.mxd.dao.EmployeeMapper;
import com.xawl.mxd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }


    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return
     */
    public boolean checkUser(String empName){
        return employeeMapper.countByEmpName(empName) == 0;
    }


    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    public Employee selectEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    /**
     * 员工更新
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /*
        员工删除
        @param id  员工id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List ids) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        //delete from xxx where emp_id in (1,2,3)
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}
