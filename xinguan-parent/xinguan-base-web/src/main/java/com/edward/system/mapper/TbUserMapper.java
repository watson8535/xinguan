package com.edward.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edward.system.entity.TbUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Edward
 * @since 2021-03-09
 */
public interface TbUserMapper extends BaseMapper<TbUser> {

    IPage<TbUser> findUserPage(Page<TbUser> page, @Param(Constants.WRAPPER) QueryWrapper<TbUser> wrapper);
}
