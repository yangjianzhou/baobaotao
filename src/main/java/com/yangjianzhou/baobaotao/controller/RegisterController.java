package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.exception.UserExistException;
import com.yangjianzhou.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangjianzhou on 15-6-14.
 */
@Controller
public class RegisterController extends  BaseController {
    /**
     * 自动注入
     */
    @Autowired
    private UserService userService;



    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request,User user) throws Exception{
        ModelAndView view = new ModelAndView();
        view.setViewName("/success");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
            view.setViewName("forward:/register.jsp");
        }
        setSessionUser(request,user);
        return view;
    }
}
