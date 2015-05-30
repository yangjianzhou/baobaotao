package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.bean.LoginFromBean;
import com.yangjianzhou.baobaotao.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.yangjianzhou.baobaotao.service.UserService ;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	public String loginPage() {
		return "login";
	}

	public ModelAndView loginCheck(HttpServletRequest request, LoginFromBean loginCommand) {
		boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		if (!isValidUser) {
			return new ModelAndView("login", "error", "用户名或密码错误。");
		} else {
			UserBean user = userService.findUserByUserName(loginCommand.getUserName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}
}
