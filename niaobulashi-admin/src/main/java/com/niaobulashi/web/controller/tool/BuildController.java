package com.niaobulashi.web.controller.tool;

import com.niaobulashi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: niaobulashi-fast
 * @description: build 表单构建
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 21:08
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController {
    private String prefix = "tool/build";

    @RequiresPermissions("tool:build:view")
    @GetMapping()
    public String build()
    {
        return prefix + "/build";
    }
}
