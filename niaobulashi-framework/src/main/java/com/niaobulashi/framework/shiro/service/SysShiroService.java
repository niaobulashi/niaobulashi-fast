package com.niaobulashi.framework.shiro.service;

import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.framework.shiro.session.OnlineSession;
import com.niaobulashi.system.domain.SysUserOnline;
import com.niaobulashi.system.service.SysUserOnlineService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @program: niaobulashi-framework
 * @description: 会话db操作
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-02 23:55
 */
@Component
public class SysShiroService {

    @Autowired
    private SysUserOnlineService onlineService;

    /**
     * 删除会话
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession) {
        onlineService.deleteOnlineById(String.valueOf(onlineSession.getId()));
    }

    /**
     * 创建会话
     * @param sessionId 会话信息
     * @return
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnline userOnline = onlineService.selectOnlineById(String.valueOf(sessionId));
        return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
    }

    /**
     * 创建会话
     * @param userOnline 会话信息
     * @return
     */
    public Session createSession(SysUserOnline userOnline) {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtils.isNotNull(userOnline)) {
            onlineSession.setId(userOnline.getSessionId());
            onlineSession.setHost(userOnline.getIpaddr());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setDeptName(userOnline.getDeptName());
            onlineSession.setLoginName(userOnline.getLoginName());
            onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
            onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
            onlineSession.setTimeout(userOnline.getExpireTime());
        }
        return onlineSession;
    }
}
