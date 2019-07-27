package com.niaobulashi.controller.system;

import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.common.core.page.TableDataInfo;
import com.niaobulashi.system.domain.SysUser;
import com.niaobulashi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: niaobulashi-fast
 * @description: 用户信息 控制层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 01:16
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    private String prefix = "system/user";

    @Autowired
    private SysUserService userService;

    /**
     * 根据条件分页查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
