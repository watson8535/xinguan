package com.edward.system.mapper;

import com.edward.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Edward
 * @since 2021-03-10
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> findDeptAndCount();
}
