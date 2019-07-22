package com.niaobulashi.core.domain;


import com.niaobulashi.utils.StringUtils;

import java.util.HashMap;

/**
 * @program: niaobulashi-fast
 * @description: 自定义响应结构
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-21 00:10
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /** 成功 */
        SUCCESS(100),
        /** 警告 */
        WARN(200),
        /** 错误 */
        ERROR(500);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /** 状态类型 */
    private Type type;

    /** 状态码 */
    private int code;

    /** 返回内容 */
    private String msg;

    /** 数据对象 */
    private Object data;

    /**
     * 初始化一个新的AjaxResult对象，使其表示一个空消息
     */
    public AjaxResult () {

    }

    /**
     * 初始化一个新创建的AjaxResult对象
     * @param type
     * @param msg
     */
    public AjaxResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    public AjaxResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {

        }
    }

    public static void main(String[] args) {
        System.out.println(new AjaxResult(Type.SUCCESS, "123"));
    }
}
