package com.niaobulashi.web.exception;

import com.niaobulashi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @program: niaobulashi-framework
 * @description: 全局异常处理qo
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-20 23:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return AjaxResult.error("不支持‘ " + e.getMethod() + "' 请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFound(RuntimeException e) {
        logger.error("运行时异常：", e);
        return AjaxResult.error("运行时异常：" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return AjaxResult.error("服务器错误，请联系管理员");
    }
}
