package com.niaobulashi.common.annotation;


import com.niaobulashi.common.enums.BusinessType;
import com.niaobulashi.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @program: niaobulashi-fast
 * @description: 自定义操作日志记录注解
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-13 13:34
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
