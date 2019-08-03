package com.niaobulashi.system.mapper;

import com.niaobulashi.system.domain.SysUser;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 用户表 数据层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-23 23:24
 */
public interface SysUserMapper {

    /**
     * 根据条件分页查询用户列表
     * @param sysUser   用户列表
     * @return  用户信息List
     */
    public List<SysUser> selectUserList(SysUser sysUser);
}
