package com.niaobulashi.common.enums;

/**
 * @program: niaobulashi-common
 * @description: 用户会话
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public enum OnlineStatus {
    on_line("在线"),
    off_line("离线");

    private final String info;

    OnlineStatus(String info) {
        this.info = info;
    }
}
