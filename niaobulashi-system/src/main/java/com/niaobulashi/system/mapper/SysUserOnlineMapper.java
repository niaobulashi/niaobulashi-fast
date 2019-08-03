package com.niaobulashi.system.mapper;

import com.niaobulashi.system.domain.SysUserOnline;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 在线用户 数据层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:37
 */
@Mapper
public interface SysUserOnlineMapper {

    /**
     * 根据会话ID查询会话信息
     * @param sessionId 会话ID
     * @return  在线用户信息
     */
    public SysUserOnline selectOnlineById(String sessionId);

    /**
     * 根据会话ID删除会话信息
     * @param sessionId 会话ID
     * @return  在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     * @param online 会话信息
     * @return
     */
    public SysUserOnline saveOnline(SysUserOnline online);

    /**
     * 查询会话信息List
     * @param online 会话信息
     * @return 会话信息List
     */
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline online);

    /**
     * 查询过期会话集合
     * @param lastAccessTime
     * @return
     */
    public List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);
}
