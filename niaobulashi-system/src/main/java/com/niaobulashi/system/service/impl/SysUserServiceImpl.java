package com.niaobulashi.system.service.impl;

import com.niaobulashi.system.domain.SysUser;
import com.niaobulashi.system.mapper.SysUserMapper;
import com.niaobulashi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: niaobulashi-fast
 * @description: 用户表 业务层处理层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-23 23:27
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据条件分页查询用户列表
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return sysUserMapper.selectUserList(user);
    }
}
