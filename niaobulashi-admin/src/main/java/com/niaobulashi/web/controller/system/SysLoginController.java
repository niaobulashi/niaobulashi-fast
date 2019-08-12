package com.niaobulashi.web.controller.system;

import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.common.core.domain.AjaxResult;
import com.niaobulashi.common.utils.ServletUtils;
import com.niaobulashi.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(e.getMessage());
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "/error/unauth";
    }
}
