package com.lening.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: mybatisplus_01_quickstart
 * @BelongsPackage: com.lening
 * @Author: 马晓锋
 * @CreateTime: 2022-09-14  14:13
 * @Description: mybatisPlus拦截器
 * @Version: 1.0
 */
@Configuration
public class MpConfig {
    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor(){
        //定义mp拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //添加具体的拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //添加乐观锁的拦截器 实现锁机制对应的动态sql语句拼装
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
