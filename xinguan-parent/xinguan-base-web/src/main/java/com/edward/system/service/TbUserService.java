package com.edward.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edward.system.entity.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Edward
 * @since 2021-03-09
 */
public interface TbUserService extends IService<TbUser> {
    IPage<TbUser> findUserPage(Page<TbUser> page, QueryWrapper<TbUser> wrapper);

    /**
     * 添加用户
     * @param user  用户对象
     */
    void addUser(TbUser user);
}
