package com.niaobulashi.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niaobulashi.common.core.domain.AjaxResult;
import com.niaobulashi.common.core.page.PageDomain;
import com.niaobulashi.common.core.page.TableDataInfo;
import com.niaobulashi.common.core.page.TableSupport;
import com.niaobulashi.common.utils.DateUtils;
import com.niaobulashi.common.utils.ServletUtils;
import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.common.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @program: niaobulashi-fast
 * @description: web层通用数据处理
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 01:17
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {

            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return ServletUtils.getSession();
    }

    /**
     * 相应请求分页数据
     * @param list
     * @return
     */
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspInfo = new TableDataInfo();
        rspInfo.setCode(0);
        rspInfo.setRows(list);
        rspInfo.setTotal(new PageInfo(list).getTotal());
        return rspInfo;
    }

    /**
     * 响应返回结果
     * @param rows  影响行数
     * @return
     */
    public AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     * @return
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功消息
     * @return
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误消息码
     */
    public AjaxResult error(AjaxResult.Type type, String message) {
        return new AjaxResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
