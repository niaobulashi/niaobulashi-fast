package com.niaobulashi.web.controller.monitor;

import com.niaobulashi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @program: niaobulashi-admin
 * @description: 用户操作
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 12:19
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController {
    private String prefix = "/druid";

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index() {
        return redirect(prefix + "/index");
    }
}
