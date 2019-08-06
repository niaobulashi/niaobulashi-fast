package com.niaobulashi.common.exception.user;

/**
 * @program: niaobulashi-common
 * @description: 角色锁定异常类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class RoleBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked", null);
    }
}