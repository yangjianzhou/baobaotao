package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.formBean.LoginFromBean;
import com.yangjianzhou.baobaotao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.yangjianzhou.baobaotao.utils.CommonConstant.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request, LoginFromBean loginCommand) throws Exception {
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    public ModelAndView login(HttpServletRequest request, User user) throws Exception {
        User loginUser = userService.findUserByUserName(user.getUserName());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/login.jsp");
        if (loginUser == null) {
            mav.addObject("errorMsg", "user not exist");
        } else if (!loginUser.getPassword().equals(user.getPassword())) {
            mav.addObject("errorMsg", "password is not correct");
        } else if (loginUser.getLocked() == Boolean.TRUE) {
            mav.addObject("errorMsg", "user has bean locked , can't login");
        } else {
            loginUser.setLastIp(request.getRemoteAddr());
            loginUser.setLastVisit(new Date());
            userService.loginSuccess(loginUser);
           setSessionUser(request,loginUser);

            String toUrl = (String) request.getSession().getAttribute(LOGIN_TO_URL);
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            mav.setViewName("redirect:"+toUrl);
        }
        return mav;
    }

    public  String logout(HttpSession session){
        session.removeAttribute(USER_CONTEXT);
        return "forward:/index.jsp";
    }
}
