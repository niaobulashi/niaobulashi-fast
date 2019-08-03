package com.niaobulashi.common.exception.user;

/**
 * @program: niaobulashi-common
 * @description: 用户错误记数异常类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class UserPasswordRetryLimitCountException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount) {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
