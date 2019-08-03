package com.niaobulashi.framework.shiro.service;

import com.niaobulashi.common.constant.Constants;
import com.niaobulashi.common.constant.ShiroConstants;
import com.niaobulashi.common.exception.user.UserPasswordNotMatchException;
import com.niaobulashi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.niaobulashi.common.utils.MessageUtils;
import com.niaobulashi.framework.manager.AsyncManager;
import com.niaobulashi.framework.manager.factory.AsyncFactory;
import com.niaobulashi.system.domain.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: niaobulashi-framework
 * @description: 登录密码方法
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 16:58
 */
@Component
public class SysPasswordService {

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    /** 最高重试次数 */
    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    /**
     * 校验用户密码
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password) {
        String loginName = user.getLoginName();
        // 定义原子Integer类，用于计算校验次数，原子操作
        AtomicInteger retryCount = loginRecordCache.get(loginName);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
            AsyncManager.me().execute(
                    AsyncFactory.recordLogininfor(
                            loginName,
                            Constants.LOGIN_FAIL,
                            MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)
                    )
            );
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }
        if (!matches(user, password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(loginName);
        }
    }

    /**
     * 匹配校验输入的密码是否正确
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    /**
     * 清除重置错误密码次数
     * @param username
     */
    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    /**
     * 加密处理
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }
}
