package com.niaobulashi.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: niaobulashi-fast
 * @description: 全局异常处理qo
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-20 23:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
}
