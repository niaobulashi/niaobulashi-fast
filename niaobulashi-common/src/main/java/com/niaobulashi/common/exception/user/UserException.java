package com.niaobulashi.common.exception.user;

import com.niaobulashi.common.exception.base.BaseException;

/**
 * @program: niaobulashi-common
 * @description: 用户信息异常类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
