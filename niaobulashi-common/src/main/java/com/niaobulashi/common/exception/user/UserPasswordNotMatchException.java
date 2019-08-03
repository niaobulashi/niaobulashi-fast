package com.niaobulashi.common.exception.user;

/**
 * @program: niaobulashi-common
 * @description: 用户密码不正确或不符合规范异常类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
