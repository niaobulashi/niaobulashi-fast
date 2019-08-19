package com.niaobulashi.common.config;

import com.niaobulashi.common.utils.ServletUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: niaobulashi-common
 * @description: 服务相关配置
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 12:19
 */
@Component
public class ServerConfig {
    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * 
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
