package com.niaobulashi.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: niaobulashi-fast
 * @description: 程序注解配置
 * @author: hulang  hulang6666@qq.com
 * @create: 2019-08-16 15:03
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.niaobulashi.*.mapper")
public class ApplicationConfig {
}

