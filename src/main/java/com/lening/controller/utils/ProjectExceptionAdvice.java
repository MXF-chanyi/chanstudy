package com.lening.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: day1005_ssmp
 * @BelongsPackage: com.lening.controller.utils
 * @Author: 马晓锋
 * @CreateTime: 2022-10-05  19:45
 * @Description: 项目异常通知或者作为springMVC的异常处理器
 * @Version: 1.0
 */
//1.要给它定义,他是controller异常处理器 @RestControllerAdvice = @ControllerAdvice+@ResponseBody
//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所以的异常信息
    @ExceptionHandler(Exception.class)
    public R doException(Exception ex){
        //记录日志
        //通知运维
        //通知邮件给开发 ex对象发给开发人员
        ex.printStackTrace();
        return new R("服务器故障，请稍后再试！");
    }
}
