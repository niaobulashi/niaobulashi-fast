package com.niaobulashi.common.exception.file;

import com.niaobulashi.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author ruoyi
 */
/**
 * @program: niaobulashi-common
 * @description: 基础异常
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:16
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
