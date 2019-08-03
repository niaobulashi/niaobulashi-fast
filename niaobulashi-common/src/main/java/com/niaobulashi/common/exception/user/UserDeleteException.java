package com.niaobulashi.common.exception.user;

/**
 * @program: niaobulashi-common
 * @description: 用户账号已被删除
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class UserDeleteException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete", null);
    }
}
