package com.niaobulashi.framework.config;

import com.niaobulashi.common.utils.StringUtils;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: niaobulashi-framework
 * @description: Shiro权限配置加载
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-07-30 23:25
 */
@Configuration
public class ShiroConfig {
    public static final String PERMISSION_STRING = "perm[\"{0}\"]";

    /** 登录地址 */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /** 权限认证失败地址 */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /** 验证码开关 */
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    /** 验证码类型 */
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    /** 设置Cookie的域名 */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /** 设置cookie的有效访问路径 */
    @Value("${shiro.cookie.path}")
    private String path;

    /** 设置HttpOnly属性 */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /** 设置Cookie的过期时间，秒为单位 */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /** Session超时时间，单位为毫秒（默认30分钟） */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /** 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟 */
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    /** 同一个用户最大会话数 */
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    /** 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户 */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 缓存管理器 使用Ehcache实现
     * @return
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("niaobulashi");
        EhCacheManager ehCacheManager = new EhCacheManager();
        if (StringUtils.isNull(cacheManager)) {
            ehCacheManager.setCacheManager(new CacheManager(getCacheManagerConfigFileInputStream()));
            return ehCacheManager;
        } else {
            ehCacheManager.setCacheManager(cacheManager);
            return ehCacheManager;
        }
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     * @return
     */
    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException("Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

}
