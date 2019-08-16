package com.niaobulashi.common.annotation;

import java.lang.annotation.*;

/**
 * @program: niaobulashi-fast
 * @description: 自定义注解防止表单重复提交
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-16 13:34
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

}