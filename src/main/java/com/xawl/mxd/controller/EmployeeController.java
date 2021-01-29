package com.xawl.mxd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xawl.mxd.bean.Employee;
import com.xawl.mxd.bean.Msg;
import com.xawl.mxd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    /**
     * 单个删除和批量删除合二为一
     * 批量删除:1-2-3
     * 单个删除:1
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids){
        //批量删除
        if(ids.contains("-")){
            String[] str_ids = ids.split("-");
           // List<Integer> list = new ArrayList<>();
            //组装id集合
            for(int i=0;i<str_ids.length;i++){
                //list.add(Integer.parseInt(str_ids[i]));
                employeeService.deleteEmp(Integer.parseInt(str_ids[i]));
            }
            //employeeService.deleteBatch(list);
        }else{
            //单个删除
            employeeService.deleteEmp(Integer.parseInt(ids));
        }

        return Msg.success();
    }

    /**
     * 根据id修改员工
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }


    /**
     * 根据id查询员工
     */

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg selectEmployeeById(@PathVariable("id") Integer id){
        Employee employee = employeeService.selectEmployeeById(id);
        return Msg.success().add("Employee",employee);
    }


    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String,Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for(FieldError fieldError:fieldErrors){
                System.out.println("错误的字段名字"+fieldError.getField());
                System.out.println("错误信息"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }


    @RequestMapping("/checkUserName")
    @ResponseBody
    public Msg checkUserName(String empName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail("用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        if(employeeService.checkUser(empName)){
            return Msg.success();
        }else{
            return Msg.fail("用户名已经存在");
        }
    }

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn){
        //引入PageHelper分页插件
        //在查询之前调用,传入页码，以及每页的大小
        PageHelper.startPage(pn,10);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就可以了
        //封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);

        return  Msg.success().add("pageInfo",page);
    }

}
