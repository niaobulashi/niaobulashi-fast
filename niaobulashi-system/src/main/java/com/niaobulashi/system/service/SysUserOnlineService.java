package com.niaobulashi.system.service;

import com.niaobulashi.system.domain.SysUserOnline;

import java.util.Date;
import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 在线用户 服务层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-02 00:09
 */
public interface SysUserOnlineService {

    /**
     * 通过会话序号查询信息
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SysUserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId);

    /**
     * 根据会话ID批量删除会话信息
     * @param sessions 会话ID
     */
    public void batchDeleteOnline(List<String> sessions);

    /**
     * 保存会话信息
     * @param online 会话信息
     */
    public void saveOnline(SysUserOnline online);

    /**
     * 查询会话信息
     * @param online 会话信息
     * @return 会话信息List
     */
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline online);

    /**
     * 强迫下线
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId);

    /**
     * 查询会话集合
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate);
}
