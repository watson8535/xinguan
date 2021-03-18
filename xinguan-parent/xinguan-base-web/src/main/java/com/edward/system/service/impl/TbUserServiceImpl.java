package com.edward.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edward.handler.BusinessException;
import com.edward.response.ResultCode;
import com.edward.system.entity.Department;
import com.edward.system.entity.TbUser;
import com.edward.system.enums.UserStatusEnum;
import com.edward.system.enums.UserTypeEnum;
import com.edward.system.mapper.DepartmentMapper;
import com.edward.system.mapper.TbUserMapper;
import com.edward.system.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Edward
 * @since 2021-03-09
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public IPage<TbUser> findUserPage(Page<TbUser> page, QueryWrapper<TbUser> wrapper) {
        return this.baseMapper.findUserPage(page, wrapper);
    }

    @Override
    public void addUser(TbUser user) {
        //判断是否添加了同一个用户
        String username = user.getUsername();
        //获取部门
        Long departmentId = user.getDepartmentId();
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count != 0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        Department department = departmentMapper.selectById(departmentId);
        if(department == null){
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(),
                    ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
        }
        String salt = UUID.randomUUID().toString().substring(0, 32);
        user.setSalt(salt);
        //设置创建时间和更新时间 用mybatis-plus的自动填充功能实现
//        user.setCreateTime(new Date());
//        user.setModifiedTime(new Date());
        //使用spring securty自带的密码加密策略
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //设置用户类型
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());
        //设置用户状态
        user.setStatus(UserStatusEnum.AVAILABLE.getStatusCode());
        //设置是否删除
        user.setDeleted(false);

        this.baseMapper.insert(user);
    }
}
