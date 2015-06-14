package com.yangjianzhou.baobaotao.exception;

import com.yangjianzhou.baobaotao.entity.User;

/**
 * Created by yangjianzhou on 15-6-14.
 */
public class UserExistException extends  Exception {

    public UserExistException(String errorMsg){
        super(errorMsg);
    }
}
