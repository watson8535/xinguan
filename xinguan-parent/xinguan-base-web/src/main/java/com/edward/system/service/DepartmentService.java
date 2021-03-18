package com.edward.system.service;

import com.edward.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Edward
 * @since 2021-03-10
 */
public interface DepartmentService extends IService<Department> {
    List<Department> findDeptAndCount();
}
