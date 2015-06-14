package com.yangjianzhou.baobaotao.enums;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Created by yangjianzhou on 15-6-13.
 */
public enum TopicType {

    ELITE("精华话题"), NORMAL("一般话题");

    private String desc;

    private TopicType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
