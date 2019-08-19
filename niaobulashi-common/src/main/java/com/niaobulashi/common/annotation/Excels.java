package com.niaobulashi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: niaobulashi-common
 * @description: Excel注解集
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 13:34
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels
{
    Excel[] value();
}