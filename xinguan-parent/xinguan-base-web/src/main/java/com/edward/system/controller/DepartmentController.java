package com.edward.system.controller;


import com.edward.handler.BusinessException;
import com.edward.response.Result;
import com.edward.response.ResultCode;
import com.edward.system.entity.Department;
import com.edward.system.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Edward
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/department")
@Api(value = "部门管理", tags = "部门人数查询")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数", notes = "从部门表中分组查询用户人数")
    @GetMapping("/findDeptAndCount")
    public Result findDeptAndCount(){
        List<Department> departments = departmentService.findDeptAndCount();
        if(departments.size() == 0){
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(), ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
        }
        return Result.ok().data("department", departments);
    }
}

