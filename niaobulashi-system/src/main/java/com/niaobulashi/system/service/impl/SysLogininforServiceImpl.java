package com.niaobulashi.system.service.impl;

import com.niaobulashi.common.core.text.Convert;
import com.niaobulashi.system.domain.SysLogininfor;
import com.niaobulashi.system.mapper.SysLogininforMapper;
import com.niaobulashi.system.service.SysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 系统访问日志情况信息 业务层处理
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 18:00
 */
@Service
public class SysLogininforServiceImpl implements SysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        logininforMapper.cleanLogininfor();
    }
}
