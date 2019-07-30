package com.niaobulashi.controller.system;

import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.common.utils.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: niaobulashi-fast
 * @description: 登录
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 20:55
 */
@Controller
public class SysLoginController extends BaseController {

    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";
    }
}
