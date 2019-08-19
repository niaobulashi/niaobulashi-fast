package com.niaobulashi.web.controller.monitor;

import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.framework.web.domain.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: niaobulashi-admin
 * @description: 服务器监控
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 12:19
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController extends BaseController {
    private String prefix = "monitor/server";

    @RequiresPermissions("monitor:server:view")
    @GetMapping()
    public String server(ModelMap mmap) throws Exception {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }
}
