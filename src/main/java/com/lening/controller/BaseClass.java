package com.lening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @BelongsProject: day1005_ssmp
 * @BelongsPackage: com.lening.controller.utils
 * @Author: 马晓锋
 * @CreateTime: 2022-10-13  20:10
 * @Description: 日志工具类
 * @Version: 1.0
 */
public class BaseClass {
    private   Class clazz ;
    //后面.class必须写成获取当前类的类型
    public static  Logger log ;
    //构造方法
    public BaseClass (){
        //初始化类对象
        clazz = this.getClass();
        //初始化logger对象
        log=LoggerFactory.getLogger(clazz);


    }

    /*private static final Class clazz = null;
    //后面.class必须写成获取当前类的类型
    public static final Logger log = LoggerFactory.getLogger(clazz);
    static{   //获取当前类的类型 实例代码快中用到了静态的东西 要改成静态代码快
        //在静态代码快中禁止使用实列变量this
         clazz=  this.getClass()
    }*/
}
