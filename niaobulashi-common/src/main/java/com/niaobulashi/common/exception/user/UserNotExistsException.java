package com.niaobulashi.common.exception.user;

/**
 * @program: niaobulashi-common
 * @description: 用户不存在异常类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class UserNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
