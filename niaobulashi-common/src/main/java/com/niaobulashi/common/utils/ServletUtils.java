package com.niaobulashi.common.utils;

import com.niaobulashi.common.core.text.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * @program: niaobulashi-fast
 * @description: 客户端工具类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 13:36
 */
public class ServletUtils {

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequset().getParameter(name);
    }

    /**
     * 获取String参数，如果为空则为默认值
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequset().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequset().getParameter(name));
    }

    /**
     * 获取Integer参数，如果为空则为默认值
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequset().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequset() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequset().getSession();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * @param response  渲染对象
     * @param str       待渲染的字符串
     * @return
     */
    public static String renderString(HttpServletResponse response, String str) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否Ajax异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }
}
