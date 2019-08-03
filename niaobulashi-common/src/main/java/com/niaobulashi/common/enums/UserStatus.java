package com.niaobulashi.common.enums;

/**
 * @program: niaobulashi-common
 * @description: 用户状态
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 17:43
 */
public enum UserStatus {

    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
