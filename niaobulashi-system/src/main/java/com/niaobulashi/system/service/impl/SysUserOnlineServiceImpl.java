package com.niaobulashi.system.service.impl;

import com.niaobulashi.common.utils.DateUtils;
import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.system.domain.SysUserOnline;
import com.niaobulashi.system.mapper.SysUserOnlineMapper;
import com.niaobulashi.system.service.SysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 在线用户 实现层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-02 00:15
 */
@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService {

    @Autowired
    private SysUserOnlineMapper userOnlineMapper;

    /**
     * 通过会话序号查询信息
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return userOnlineMapper.selectOnlineById(sessionId);
    }

    /**
     * 根据会话ID删除会话信息
     * @param sessionId 会话ID
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        // 先查询该sessionId是否存在
        SysUserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline)) {
            userOnlineMapper.deleteOnlineById(sessionId);
        }
    }

    /**
     * 根据会话ID集合批量删除会话信息
     * @param sessions 会话ID
     */
    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            SysUserOnline userOnline = selectOnlineById(sessionId);
            if (StringUtils.isNotNull(userOnline)) {
                userOnlineMapper.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 保存会话
     * @param online 会话信息
     */
    @Override
    public void saveOnline(SysUserOnline online) {
        userOnlineMapper.saveOnline(online);
    }

    /**
     * 查询会话信息List
     * @param online 会话信息
     * @return 会话信息List
     */
    @Override
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline online) {
        return userOnlineMapper.selectUserOnlineList(online);
    }

    /**
     * 强制下线
     * @param sessionId 会话ID
     */
    @Override
    public void forceLogout(String sessionId) {
        userOnlineMapper.deleteOnlineById(sessionId);
    }

    /**
     * 查询过期会话集合
     * @param expiredDate 有效期
     * @return
     */
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineMapper.selectOnlineByExpired(lastAccessTime);
    }
}
