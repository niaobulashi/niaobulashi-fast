package com.niaobulashi.web.controller.tool;

import com.niaobulashi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: niaobulashi-fast
 * @description: swagger 接口 控制层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 21:08
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController {
    @RequiresPermissions("tool:swagger:view")
    @GetMapping()
    public String index() {
        return redirect("/swagger-ui.html");
    }
}
