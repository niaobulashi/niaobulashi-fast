package com.niaobulashi.common.core.page;

import com.niaobulashi.common.constant.Constants;
import com.niaobulashi.common.utils.ServletUtils;

/**
 * @program: niaobulashi-fast
 * @description: 表格数据处理
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-27 13:30
 */
public class TableSupport {

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
