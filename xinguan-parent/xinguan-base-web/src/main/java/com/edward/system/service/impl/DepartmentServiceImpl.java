package com.edward.system.service.impl;

import com.edward.system.entity.Department;
import com.edward.system.mapper.DepartmentMapper;
import com.edward.system.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Edward
 * @since 2021-03-10
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public List<Department> findDeptAndCount() {
        return this.baseMapper.findDeptAndCount();
    }
}
