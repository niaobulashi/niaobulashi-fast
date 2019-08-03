package com.niaobulashi.common.annotation;

import java.lang.annotation.*;

/**
 * @program: niaobulashi-fast
 * @description: 数据权限过滤注解
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 13:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
