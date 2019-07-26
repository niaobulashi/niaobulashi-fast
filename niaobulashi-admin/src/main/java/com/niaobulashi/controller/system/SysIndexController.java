package com.niaobulashi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: niaobulashi-fast
 * @description: 首页
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-19 01:00
 */
@Controller
public class SysIndexController {

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }
}
