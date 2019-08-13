package com.niaobulashi.framework.web.service;

import com.niaobulashi.system.domain.SysConfig;
import com.niaobulashi.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: niaobulashi-fast
 * @description: RuoYi首创 html调用 thymeleaf 实现参数管理
 * @author: hulang  hulang6666@qq.com
 * @create: 2019-08-13 18:56
 */
@Service("config")
public class ConfigService {

    @Autowired
    private SysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数Id
     * @return
     */
    public String getKey (String configKey) {
        return configService.selectConfigByKey(configKey);
    }
}

