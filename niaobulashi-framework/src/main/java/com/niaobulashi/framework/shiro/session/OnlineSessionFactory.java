package com.niaobulashi.framework.shiro.session;

import com.niaobulashi.common.utils.IpUtils;
import com.niaobulashi.common.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: niaobulashi-fast
 * @description: 自定义sessionFactory会话
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:28
 */
@Component
public class OnlineSessionFactory implements SessionFactory {

    /**
     * 创建Session
     * @param initData
     * @return
     */
    @Override
    public Session createSession(SessionContext initData) {
        OnlineSession session = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequset().getHeader("User-Agent"));
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setId(IpUtils.getIpAddr(request));
                session.setOs(os);
                session.setBrowser(browser);
            }

        }
        return session;
    }
}
