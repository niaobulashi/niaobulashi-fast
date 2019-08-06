package com.niaobulashi.framework.shiro.web.filter;

import com.niaobulashi.common.constant.Constants;
import com.niaobulashi.common.constant.ShiroConstants;
import com.niaobulashi.common.utils.MessageUtils;
import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.framework.manager.AsyncManager;
import com.niaobulashi.framework.manager.factory.AsyncFactory;
import com.niaobulashi.framework.util.ShiroUtils;
import com.niaobulashi.system.domain.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;

/**
 * @program: niaobulashi-fast
 * @description: 退出过滤器
 * @author: hulang  hulang6666@qq.com
 * @create: 2019-08-06 14:14
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向地址
     */
    private String loginUrl;

    private Cache<String, Deque<Serializable>> cache;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try {
                SysUser user = ShiroUtils.getSysUser();
                if (StringUtils.isNotNull(user)) {
                    String loginName = user.getLoginName();
                    // 记录用户退出日志
                    AsyncManager.me().execute(
                            AsyncFactory.recordLogininfor(
                                    loginName,
                                    Constants.LOGOUT,
                                    MessageUtils.message("user.logout.success")
                            )
                    );
                    // 清除缓存
                    subject.logout();
                }
            } catch (SessionException se) {
                logger.error("logout fail", se);
            }
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            logger.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     * @param request
     * @param response
     * @param subject
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url)) {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }

    /**
     * 设置Cache的key的前缀
     * @param cacheManager
     */
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USERCACHE);
    }
}

