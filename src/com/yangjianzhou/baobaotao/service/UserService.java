package com.yangjianzhou.baobaotao.service;

import com.yangjianzhou.baobaotao.bean.LoginLogBean;
import com.yangjianzhou.baobaotao.bean.UserBean;
import com.yangjianzhou.baobaotao.dao.LoginLogDao;
import com.yangjianzhou.baobaotao.dao.UserDao;
import com.yangjianzhou.baobaotao.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}

	public UserBean findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	public void loginSuccess(UserBean user) {
		user.setCredits(5 + user.getCredits());
		LoginLogBean loginLog = new LoginLogBean();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		loginLog.setLoginLogId(IDGenerator.generatorID());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}

}
