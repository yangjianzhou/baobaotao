package com.yangjianzhou.baobaotao.enums;

/**
 * Created by yangjianzhou on 15-6-12.
 * 我们的世界
 */
public enum UserType {
    NORMAL("普通用户"),ADMINISTRATOR("管理员");

    public  String  desc ;

    private  UserType(String desc){
        this.desc =desc ;
    }

    public   String  getDesc(){
        return this.desc ;
    }

}
