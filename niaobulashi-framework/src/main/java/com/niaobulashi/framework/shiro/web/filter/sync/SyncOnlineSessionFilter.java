package com.niaobulashi.framework.shiro.web.filter.sync;

import com.niaobulashi.common.constant.ShiroConstants;
import com.niaobulashi.framework.shiro.session.OnlineSession;
import com.niaobulashi.framework.shiro.session.OnlineSessionDAO;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @program: niaobulashi-framework
 * @description: 同步Session数据到Db
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-06 23:32
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter {

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mapperValue) throws Exception {
        OnlineSession session = (OnlineSession) request.getAttribute(ShiroConstants.ONLINE_SESSION);
        // 如果session stop了 也不同步
        // session停止时间，如果stopTimestamp不为null，则代表已停止
        if (session != null && session.getUserId() != null && session.getStopTimestamp() != null) {
            onlineSessionDAO.syncToDb(session);
        }
        return true;
    }
}
