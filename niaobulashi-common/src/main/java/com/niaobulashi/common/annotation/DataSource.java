package com.niaobulashi.common.annotation;

import com.niaobulashi.common.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @program: niaobulashi-fast
 * @description: 自定义多数据源切换注解
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 13:34
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
