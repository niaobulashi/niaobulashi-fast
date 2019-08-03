package com.niaobulashi.system.service;

import com.niaobulashi.system.domain.SysUser;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 用户表 业务层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-23 23:27
 */
public interface SysUserService {

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);
}
