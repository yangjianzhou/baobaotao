package com.yangjianzhou.baobaotao.service;

import com.yangjianzhou.baobaotao.dao.LoginLogDao;
import com.yangjianzhou.baobaotao.dao.UserDao;
import com.yangjianzhou.baobaotao.entity.LoginLog;
import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.enums.UserType;
import com.yangjianzhou.baobaotao.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public void register(User user) throws Exception {
        User tempUser = this.findUserByUserName(user.getUserName());
        if (tempUser != null) {
            throw new Exception();
        } else {
            user.setUserId(IDGenerator.generatorID());
            user.setCredits(100);
            user.setUserType(UserType.NORMAL);
            userDao.insert(user);
        }
    }

    public  User getUserById(String  id ){
        return userDao.getById(id);
    }

    public void lockUser(String  userName)throws Exception{
        User user = userDao.getUserByUserName(userName);
        user.setLocked(Boolean.TRUE);
        userDao.update(user);
    }

    public void unlockUser(String userName)throws  Exception{
        User user = userDao.getUserByUserName(userName);
        user.setLocked(Boolean.FALSE);
        userDao.update(user);
    }

    public List<User> queryUsersByUserName(String userName) throws Exception{
        return userDao.queryUsersByUserName(userName);
    }

    public boolean hasMatchUser(String userName, String password) throws Exception {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) throws Exception {
        return userDao.getUserByUserName(userName);
    }

    public void loginSuccess(User user) throws Exception {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLog.setLoginLogId(IDGenerator.generatorID());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

    public   List<User>  getAllUsers(){
        List<User>  users = new ArrayList<User>();
        return users;
    }


}
