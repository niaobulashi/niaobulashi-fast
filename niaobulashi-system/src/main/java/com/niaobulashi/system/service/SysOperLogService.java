package com.niaobulashi.system.service;

import com.niaobulashi.system.domain.SysOperLog;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 操作日志 服务层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 11:10
 */
public interface SysOperLogService {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();

}
