package com.niaobulashi.web.controller.system;

import com.niaobulashi.common.config.Global;
import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.framework.util.ShiroUtils;
import com.niaobulashi.system.domain.SysMenu;
import com.niaobulashi.system.domain.SysUser;
import com.niaobulashi.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: niaobulashi-fast
 * @description: 首页
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-19 01:00
 */
@Controller
public class SysIndexController extends BaseController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 系统首页
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        // 获取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据userId获取菜单数据
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        modelMap.put("menus", menus);
        modelMap.put("user", user);
        modelMap.put("copyrightYear", Global.getCopyrightYear());
        modelMap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    /**
     * 系统介绍
     * @param modelMap
     * @return
     */
    @GetMapping("/system/main")
    public String main(ModelMap modelMap) {
        modelMap.put("version", Global.getVersion());
        return "main";
    }
}
