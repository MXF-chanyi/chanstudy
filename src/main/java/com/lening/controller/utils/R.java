package com.lening.controller.utils;

import lombok.Data;

/**
 * @BelongsProject: springboot
 * @BelongsPackage: com.lening.controller.utils
 * @Author: 马晓锋
 * @CreateTime: 2022-10-03  04:30
 * @Description: 前后端数据协议
 * @Version: 1.0
 */
@Data
public class R {
    //是否成功
    private boolean flag;
    //对象
    private Object data;
    //消息
    private String msg;

    private  R(){}

    public R(Boolean flag){
        this.flag = flag;
    }

    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }

    public R(String msg){
        this.msg = msg;
    }
}
