package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.utils.CommonConstant;
import org.junit.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangjianzhou on 15-6-14.
 */
public class BaseController {

    protected static  final String ERROR_MSG_KEY = "errorMsg";

    protected User getSessionUser(HttpServletRequest request){
        return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    protected void setSessionUser(HttpServletRequest request , User user){
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT , user);
    }

    public  final  String getApplicationBaseUrl(HttpServletRequest request , String url){
        return request.getContextPath()+url;
    }
}
