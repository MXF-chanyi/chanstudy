package com.lening.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
@RestController
@RequestMapping("/logs")
public class LogController extends BaseClass {

    //1.创建记录日志的对象
/*
    private static final Logger log = LoggerFactory.getLogger(LogController.class);
*/


    @GetMapping
    public String getAll(){
        log.trace("堆栈trace...级别过低");
        log.debug("给程序员调试代码debug..");
        log.info("运行信息info...");
        log.warn("warn警告...");
        log.error("error错误...");
/*
        log.fatal()//没有提供相应的api为什么  error和fatal合并了都叫error  在日志系统中定义为灾难性后果，一般用不到
*/
        return "springBoot";
    }


}

