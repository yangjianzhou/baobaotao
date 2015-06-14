package com.yangjianzhou.baobaotao.entity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder ;
/**
 * Created by yangjianzhou on 15-5-31.
 */
public class BaseBean implements Serializable {

    @Override
    public String toString() {
        return  ToStringBuilder.reflectionToString(this);
    }
}
