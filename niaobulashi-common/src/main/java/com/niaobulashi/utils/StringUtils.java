package com.niaobulashi.utils;


import com.niaobulashi.core.text.StrFormatter;

import java.util.Collection;
import java.util.Map;

/**
 * @program: niaobulashi-fast
 * @description: 字符串工具类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-21 13:55
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    /**
     * 获取参数不为空值
     * @param value 需要判断的value
     * @param defaultValue
     * @param <T>
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 判断一个Collection是否为空，包含了List，Set，Queue
     * @param coll  Collection
     * @return  true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断一个Collection是否非空，包含了List，Set，Queue
     * @param coll  Collection
     * @return  true：非空 false：为空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断一个对象数据是否为空
     * @param objects 要判断的objects
     * @return  true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    /**
     * 判断一个对象数据是否非空
     * @param objects 要判断的objects
     * @return  true：非空 false：为空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map是否为空
     * @param map 要判断的map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否非空
     * @param map 要判断的map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个字符串是否为空
     * @param str 要判断的字符串
     * @return  true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 判断一个字符串是否非空
     * @param str 要判断的字符串
     * @return  ture：非空 false：为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断一个对象是否为空
     * @param object    Object
     * @return  true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断一个对象是否非空
     * @param object    Object
     * @return  true：非空 false：为空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断一个对象是否为数组类型
     * @param object    Object
     * @return  true：是数组 false：不是数据
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 截取字符串
     * @param str   字符串
     * @param start 开始
     * @return
     */
    public static String substring(final String str, int start) {
        // 判断是否为空
        if (str == null) {
            return NULLSTR;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }
        return str.substring(start);
    }

    /**
     * 截取字符串
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return NULLSTR;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     * @return
     */
    public static String format(String template, Object... params) {
        if (isEmpty(template) || isEmpty(params)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

}
